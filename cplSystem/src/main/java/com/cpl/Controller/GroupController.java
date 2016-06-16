package com.cpl.Controller;

import com.cpl.dataModelMain.Domain.RepositoryRole;
import com.cpl.httpSender.HttpSender;
import com.cpl.service.PermissionService;
import com.cpl.service.ProjectService;
import com.cpl.service.RepositoryRoleService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@CrossOrigin
@RestController
public class GroupController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RepositoryRoleService roleService;
    @Autowired
    private PermissionService permissionService;

    private HttpSender sender = new HttpSender();
    /* permission index:
            6 : create team
            7 : create project
            8 : add member
            9 : delete member
     */

    //get all group
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public @ResponseBody
    String get(String private_token){
        JSONArray result = sender.getRequest("groups/", "private_token=" + private_token);
        return result.toString();
    }

    //get all group project
    @RequestMapping(value = "/group/allProject", method = RequestMethod.GET)
    public @ResponseBody
    String getAll(String private_token){
        JSONArray groups = sender.getRequest("groups/", "private_token=" + private_token);
        JSONArray allProject = sender.getRequest("projects", "private_token=" + private_token);
        JSONArray result = new JSONArray();
        for(int i = 0;i < groups.length();i++){
            int gid = groups.getJSONObject(i).getInt("id");
            System.out.println(gid);

            for(int j = 0;j < allProject.length();j++) {
                if (allProject.getJSONObject(j).getJSONObject("namespace").getInt("id") == gid) {
                    JSONObject obj = allProject.getJSONObject(j);
                    result.put(obj);
                }
            }
        }

        return result.toString();
    }

    //get group detail
    @RequestMapping(value = "/group/detail", method = RequestMethod.GET)
    public @ResponseBody
    String getDetail(String private_token, int gid){
        JSONArray result = sender.getRequest("groups/" + gid, "private_token=" + private_token);
        return result.toString();
    }

    //add group
    @RequestMapping(value = "/group/add/group", method = RequestMethod.GET)
    public @ResponseBody
    Boolean addGroup(String private_token, int oid, String name,String path, String description){
        int role = roleService.getRole(oid);
        boolean permission = permissionService.getPermissionValue(role, 6);
        if(permission) {
            //create the project with the namespace_id = gid
            JSONArray result = sender.postRequest("groups", "name=" + name + "&private_token=" + private_token + "&path=" + path + "&description=" + description);
            return true;
        }else
            return false;
    }

    //get all project
    @RequestMapping(value = "/group/project", method = RequestMethod.GET)
    public @ResponseBody
    String getProject(String private_token, int gid){
        JSONArray result = sender.getRequest("groups/" + gid + "/projects", "private_token=" + private_token);
        return result.toString();
    }

    //get all member
    @RequestMapping(value = "/group/member", method = RequestMethod.GET)
    public @ResponseBody
    String getMember(String private_token ,int gid){
        JSONArray result = sender.getRequest("groups/" + gid + "/members", "private_token=" + private_token);

        Iterable<RepositoryRole> rList = roleService.getAll();
        Iterator iter = rList.iterator();
        while (iter.hasNext()) {
            RepositoryRole r = (RepositoryRole) iter.next();
            for (Object o : result) {
                JSONObject obj = (JSONObject) o;
                if (obj.getInt("id") == r.getId()) {
                    obj.put("type_id", r.getRole());
                    break;
                }
            }
        }
        return result.toString();
    }

    //add project
    @RequestMapping(value = "/group/add/project", method = RequestMethod.GET)
    public @ResponseBody
    String addProject(String private_token, int gid, int oid, String name, String description){
        int role = roleService.getRole(oid);
        boolean permission = permissionService.getPermissionValue(role, 7);
        if(permission) {
            //create the project with the namespace_id = gid

            JSONArray result = sender.postRequest("projects", "name=" + name + "&private_token=" + private_token + "&namespace_id=" + gid + "&description=" + description + "&visibility_level=20");

            System.out.println(result);
            projectService.modifyType(result.getJSONObject(0).getInt("id"), -1, 0);
            return result.toString();
        }else
            return "";
    }

    //delete project
    @RequestMapping(value = "/group/delete/project", method = RequestMethod.GET)
    public @ResponseBody
    Boolean deleteProject(String private_token,int oid, int pid){
        int role = roleService.getRole(oid);
        boolean permission = permissionService.getPermissionValue(role, 7);
        System.out.println(permission);
        if(permission) {
            sender.deleteRequest("projects/" + pid,
                    "private_token=" + private_token);
            return true;
        }else{
            return false;
        }
    }

    //add member
    @RequestMapping(value = "/group/add/member", method = RequestMethod.GET)
    public @ResponseBody
    Boolean addMember(String private_token,int oid, int gid, int uid){
        int role = roleService.getRole(oid);
        boolean permission = permissionService.getPermissionValue(role, 8);
        if(permission) {
            //set access_level to 50 as owner
            JSONArray result = sender.postRequest("groups/" + gid + "/members",
                    "private_token=" + private_token + "&user_id=" + uid + "&access_level=" + 50);
            return true;
        }else{
            return false;
        }
    }

    //delete member
    @RequestMapping(value = "/group/delete/member", method = RequestMethod.GET)
    public @ResponseBody
    Boolean deleteMember(String private_token,int oid, int gid, int uid){
        int role = roleService.getRole(oid);
        boolean permission = permissionService.getPermissionValue(role, 9);
        if(permission) {
            sender.deleteRequest("groups/" + gid + "/members/" + uid,
                    "private_token=" + private_token);
            return true;
        }else{
            return false;
        }
    }
}


