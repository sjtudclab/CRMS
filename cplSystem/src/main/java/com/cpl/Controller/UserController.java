package com.cpl.Controller;

import com.cpl.dataModelMain.Domain.RepositoryRole;
import com.cpl.httpSender.HttpSender;
import com.cpl.service.RepositoryRoleService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private RepositoryRoleService roleService;

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public
    @ResponseBody
    String login(String username, String password) {
        HttpSender sender = new HttpSender();
        JSONArray result = sender.postRequest("session", "login=" + username + "&password=" + password);

        if (result != null) {
            int id = result.getJSONObject(0).getInt("id");
            String private_token = result.getJSONObject(0).getString("private_token");
            int role = roleService.getRole(id);

            if (roleService.isFindId(id)) {
                String res = "{\"id\":\"" + id + "\",\"private_token\":\"" + private_token + "\",\"role\":\"" + role + "\"}";
                return res;
            } else
                return null;
        }
        return null;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public
    @ResponseBody
    String getInfo(String private_token, int id) {
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("users/" + id, "private_token=" + private_token);
        int role = roleService.getRole(id);
        result.getJSONObject(0).put("role", role);
        return result.toString();
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public
    @ResponseBody
    String all(String private_token) {
        HttpSender sender = new HttpSender();
        JSONArray result = sender.getRequest("users", "private_token=" + private_token);

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

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean create(String private_token, String username, String name, String password, String email, int role) {
        HttpSender sender = new HttpSender();
        JSONArray result = sender.postRequest("users",
                "private_token=" + private_token + "&username=" + username +
                        "&password=" + password + "&name=" + name + "&email=" + email);
        roleService.updateRole(result.getJSONObject(0).getInt("id"), role);
        result.getJSONObject(0).put("role", role);
        return true;
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public
    @ResponseBody
    Boolean delete(String private_token, int id) {
        HttpSender sender = new HttpSender();
        sender.deleteRequest("users/" + id , "private_token=" + private_token);
        roleService.updateRole(id, -1);
        return true;
    }
}


