package edu.note.noteservice.security;

import edu.note.noteservice.exceptions.AuthFailureException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {
    private final AuthService authService;

    public SecurityAspect(AuthService authService) {
        this.authService = authService;
    }

    @Around("@annotation(edu.note.noteservice.security.Authenticated)")
    public Object authenticate(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        String token = request.getHeader("Authorization").split(" ")[1];
        if (authService.authenticate(token)) {
            return joinPoint.proceed();
        } else {
            throw new AuthFailureException("Cannot auth user");
        }
    }
}
