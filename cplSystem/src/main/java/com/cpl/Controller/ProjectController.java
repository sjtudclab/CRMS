package com.cpl.Controller;

import com.cpl.httpSender.HttpSender;
import com.cpl.service.PermissionService;
import com.cpl.service.ProjectService;
import com.cpl.service.RepositoryRoleService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProjectController {
    @Autowired
    private RepositoryRoleService roleService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/project/get", method = RequestMethod.GET)
    public @ResponseBody
    String getAll(String private_token, int oid){
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("projects", "private_token=" + private_token);

        int role = roleService.getRole(oid);
        for(int i = 0;i < result.length();i++){
            int type = projectService.getType(result.getJSONObject(i).getInt("id"));
            int state = projectService.getState(result.getJSONObject(i).getInt("id"));
            if(permissionService.getPermissionValue(role, type) || type == -1){
                result.getJSONObject(i).put("type_id", type);
                result.getJSONObject(i).put("completed", state);
            }else{
                result.remove(i);
                i--;
            }
        }

        return result.toString();
    }

    @RequestMapping(value = "/project/getPart", method = RequestMethod.GET)
    public @ResponseBody
    String getAll(String private_token, int oid, int part){
        /* part:    0 - state = 0 -> internal
                    1 - state = 1 -> public
        */
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("projects", "private_token=" + private_token);

        int role = roleService.getRole(oid);
        for(int i = 0;i < result.length();i++){
            int id = result.getJSONObject(i).getInt("id");
            int state = projectService.getState(id);
            System.out.println("id:" + id + " state:" + state);
            if((state == 0 && part == 0) || (state == 1 && part == 1) || (state == 1 && part == 0)){
                System.out.println(result.getJSONObject(i).getInt("id"));
                int type = projectService.getType(result.getJSONObject(i).getInt("id"));
                if(permissionService.getPermissionValue(role, type) ){
                    result.getJSONObject(i).put("type_id", type);
                }else{
                    result.remove(i);
                    i--;
                }
            }else{
                result.remove(i);
                i--;
            }
        }

        return result.toString();
    }

    @RequestMapping(value = "/project/getDetail", method = RequestMethod.GET)
    public @ResponseBody
    String getDetail(int id, String private_token){
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("projects/" + id,"private_token=" + private_token);
        for(int i = 0;i < result.length();i++){
            int type = projectService.getType(result.getJSONObject(i).getInt("id"));
            result.getJSONObject(i).put("type_id", type);
        }

        System.out.println(result);
        return result.toString();
    }

    @RequestMapping(value = "/project/modify", method = RequestMethod.GET)
    public @ResponseBody
    Boolean modify(String private_token, int id, int type, String name, String description){
        HttpSender sender = new HttpSender();
        sender.putRequest("projects/" + id,
                "private_token=" + private_token + "&name=" + name + "&description=" + description);
        int state = projectService.getState(id);
        projectService.modifyType(id, type, state);
        return true;
    }

    @RequestMapping(value = "/project/branch", method = RequestMethod.GET)
    public @ResponseBody
    String branch(String private_token, int id){
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("projects/" + id + "/repository/branches",
                "private_token=" + private_token);
        return result.toString();
    }
}

