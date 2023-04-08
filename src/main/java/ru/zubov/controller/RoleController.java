package ru.zubov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubov.entity.Role;
import ru.zubov.service.RoleService;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/search")
    public ResponseEntity<List<Role>> search(@RequestParam("email") String email) {
        if (isBlank(email)) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(roleService.findAll(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> search(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.findById(id).orElse(null));
    }
}
