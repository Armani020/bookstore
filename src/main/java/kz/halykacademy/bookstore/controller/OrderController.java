package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.OrderDto.Request;
import kz.halykacademy.bookstore.dto.OrderDto.Response;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Response.Created> save(@Validated @RequestBody Request.Create request) {
        return new ResponseEntity<>(
                orderService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response.All> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(
                orderService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<Page<Response.All>> getOrders(@RequestParam String login, Pageable pageable) {
        return new ResponseEntity<>(
                orderService.findAll(login, pageable),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response.All> updateOrder(@PathVariable Long id, @Valid @RequestBody Request.Update request) {
        return new ResponseEntity<>(
                orderService.update(id, request),
                HttpStatus.OK
        );
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Response.All> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody Request.UpdateStatus request) {
        return new ResponseEntity<>(
                orderService.updateStatus(id, request),
                HttpStatus.OK
        );
    }

}
