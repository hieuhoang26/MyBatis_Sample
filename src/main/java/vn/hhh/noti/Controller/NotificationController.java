package vn.hhh.noti.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.hhh.noti.Dto.NotificationRequest;
import vn.hhh.noti.Dto.ResponseData;
import vn.hhh.noti.Dto.ResponseError;
import vn.hhh.noti.Model.Notification;
import vn.hhh.noti.Service.NotificationService;
import vn.hhh.noti.Utils.Status;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller")
@RequestMapping("/api/v1/noti")
public class NotificationController {

    final NotificationService notificationService;

    @Operation(summary = "Get All", description = "Return list ")
    @GetMapping(value = "/all")
    public ResponseData<?> getAll() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get All", notificationService.getAllNoti());
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
    public ResponseData<?> insert(@RequestBody NotificationRequest notification) {
        try {

            return new ResponseData<>(HttpStatus.CREATED.value(), "Insert success" ,notificationService.insert(notification));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @Operation(summary = "Change Status ", description = " ")
    @PatchMapping()
    public ResponseData<?> changeStatus(@RequestParam Integer id, @RequestParam Status status) {
        try {
            notificationService.changeStatus(id,status);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "change success" );
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @Operation(summary = "Delete By Id ", description = " ")
    @DeleteMapping("/{id}")
    public ResponseData<?> delete(@PathVariable Integer id) {
        try {
            notificationService.delete(id);
            return new ResponseData<>(HttpStatus.RESET_CONTENT.value(), "delete success" );
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
