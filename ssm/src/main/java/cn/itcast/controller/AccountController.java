package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * web层
 * @author goodtime
 * @create 2020-02-01 2:31 下午
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Account> all = accountService.findAll();
        model.addAttribute("list",all);
        return "list";
    }

    @RequestMapping("/save")
    public String findAll(Account account, HttpServletRequest a, HttpServletResponse httpServletResponse) throws Exception{
        accountService.saveAccount(account);
        httpServletResponse.sendRedirect(a.getContextPath()+"/account/findAll");
        return null;
    }

}
