package app.lyd.springbootproject.app.web;

import app.lyd.springbootproject.app.service.UserService;
import app.lyd.springbootproject.app.web.query.UserInfoQuery;
import app.lyd.springbootproject.app.web.result.UserInfoResult;
import app.lyd.springbootproject.base.web.result.PageResult;
import app.lyd.springbootproject.base.web.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Result<PageResult<UserInfoResult>> getUsers(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String username,
                                                       @RequestParam(required = false) String email,
                                                       @RequestParam(required = false) String active,
                                                       @RequestParam(required = false) String createTimeStart,
                                                       @RequestParam(required = false) String createTimeEnd,
                                                       @RequestParam(required = false) String lastModifyStart,
                                                       @RequestParam(required = false) String lastModifyEnd,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size,
                                                       @RequestParam(required = false) String order) {
        UserInfoQuery query = UserInfoQuery.builder()
                .name(name).username(username).email(email).active(active)
                .createTimeStart(createTimeStart).createTimeEnd(createTimeEnd)
                .lastModifyStart(lastModifyStart).lastModifyEnd(lastModifyEnd)
                .page(page, size).order(order).build();
        return new Result(userService.getUsersByCondition(query));
    }
}
