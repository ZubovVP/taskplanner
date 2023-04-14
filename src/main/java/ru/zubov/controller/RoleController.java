package ru.zubov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubov.entity.Role;
import ru.zubov.service.RoleService;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("email") String email) {
        if (isBlank(email)) {
            return new ResponseEntity<>("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(roleService.findAll(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
