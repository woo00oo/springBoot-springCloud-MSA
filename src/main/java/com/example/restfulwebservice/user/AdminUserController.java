package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable Integer id) {
        Optional<User> optionalUser = service.findOne(id);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        User user = optionalUser
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

}

/**
 * Entity 클래스 User를 클라이언트에 return 해주는 방식은 안티 패턴..이라고 생각한다.
 * Entity 클래스는 DB와 맞닿는 핵심 도메인 클래스이기 때문에 Entity -> DTO로 변환 후 클라이언트에게 응답 하도록 하자.
 */