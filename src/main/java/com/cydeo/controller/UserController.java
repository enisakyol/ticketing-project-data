package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.impl.RoleService;
import com.cydeo.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createUser(Model model){ // Bu sayfanın ikinci aşaması + Projenin üçüncü aşaması olarak metod yazıyorum

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAllRoles()); //Bu sayfanın kaba taslak üçüncü aşaması + Projenin sonraki aşaması olarak metod yazıyorum
        model.addAttribute("users", userService.listAllUsers());

        return "/user/create";

    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());

            return "/user/create";

        }

        userService.save(user);

        return "redirect:/user/create";

    }


    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("users", userService.listAllUsers());

        return "/user/update";

    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());

            return "/user/update";

        }

        userService.update(user);

        return "redirect:/user/create";

    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
       // userService.deleteByUserName(username);
        userService.delete(username);
        return "redirect:/user/create";
    }

}
