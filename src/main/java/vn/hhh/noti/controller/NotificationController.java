package vn.hhh.noti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.hhh.noti.dto.NotificationRequest;
import vn.hhh.noti.dto.ResponseData;
import vn.hhh.noti.dto.ResponseError;
import vn.hhh.noti.service.NotificationService;
import vn.hhh.noti.utils.Status;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller")
@RequestMapping("/api/v1/noti")
public class NotificationController {

    final NotificationService notificationService;

    @Operation(summary = "Get All", description = "Return list ")
    @GetMapping(value = "/all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "10") int limit,
                                  @RequestParam(defaultValue = "0") int offset) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get All", notificationService.getAllNoti(limit, offset));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Get By Id ", description = "Return list ")
    @GetMapping()
    public ResponseData<?> getById(@RequestParam Integer id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get By Id", notificationService.getById(id));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Insert ", description = "Insert ")
    @PostMapping()
    public ResponseData<?> insert(@ModelAttribute NotificationRequest notification) {
        try {

            return new ResponseData<>(HttpStatus.CREATED.value(), "Insert success", notificationService.insert(notification));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Change Status (Push) ", description = " ")
    @PatchMapping()
    public ResponseData<?> Push(@RequestParam Integer id) {
        try {
            notificationService.push(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "push success");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Operation(summary = "Delete By Id ", description = " ")
    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable Integer id) {
        try {
            notificationService.delete(id);
            return new ResponseData<>(HttpStatus.RESET_CONTENT.value(), "delete success");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
