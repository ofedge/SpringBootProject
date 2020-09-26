package app.lyd.springbootproject.app.web;


import app.lyd.springbootproject.app.service.SessionService;
import app.lyd.springbootproject.app.web.req.LoginReq;
import app.lyd.springbootproject.app.web.result.TokenUser;
import app.lyd.springbootproject.auth.annotation.AuthIgnore;
import app.lyd.springbootproject.auth.annotation.LoginUser;
import app.lyd.springbootproject.base.web.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    @AuthIgnore
    public Result<TokenUser> signIn(@RequestBody LoginReq loginReq) {
        return new Result(sessionService.signIn(loginReq));
    }

    @GetMapping("/session")
    public Result<TokenUser> userInfo(@LoginUser TokenUser tokenUser) {
        return new Result<>(tokenUser);
    }

    @DeleteMapping("/session")
    public Result signOut(@LoginUser TokenUser tokenUser) {
        sessionService.signOut(tokenUser);
        return new Result();
    }
}
