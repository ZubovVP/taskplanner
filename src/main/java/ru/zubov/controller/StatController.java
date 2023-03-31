package ru.zubov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zubov.entity.Stat;
import ru.zubov.service.StatService;

@RestController
@AllArgsConstructor
public class StatController {
    private final StatService statService;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(statService.findStat(email));
    }
}
