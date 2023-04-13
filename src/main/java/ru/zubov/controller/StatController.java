package ru.zubov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> findByEmail(@RequestParam String email) {
        if (email == null) {
            return new ResponseEntity<>("missed param: String", HttpStatus.NOT_ACCEPTABLE);
        }
        Stat stat = statService.findStat(email);
        return stat != null ? ResponseEntity.ok(statService.findStat(email)) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
