package org.birthdayreminder.security.controller;

import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;
//TODO - testpage - удалить
@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @GetMapping("/tools")
    //дополнительно проверяет роль.
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<String> getDeveloperTools() {
        return ResponseEntity.ok("Ты разработчик!");
    }
}