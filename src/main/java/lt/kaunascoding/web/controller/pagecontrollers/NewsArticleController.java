package lt.kaunascoding.web.controller.pagecontrollers;

import lt.kaunascoding.web.model.UserSession;
import lt.kaunascoding.web.model.mysql.MysqlCommands;
import lt.kaunascoding.web.model.mysql.classes.News;
import lt.kaunascoding.web.model.mysql.classes.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewsArticleController {
    @GetMapping(value = "/straipsnis", params = {"articleid"})
    public String load(Model model,@RequestParam(value = "articleid") int articleid ){
        model = getPageModel(model);
        model.addAttribute("newsarticle",new MysqlCommands().getNewsByID(articleid));

        return "news_article";
    }

    private Model getPageModel(Model model){
        String welcome = "";
        String username = "";
        if(UserSession.getUser() != null && UserSession.getUser().getNickname() != null){
            welcome = "Sveiki "+UserSession.getUser().getName();
            username = UserSession.getUser().getNickname();
        }
        List<News> news = new MysqlCommands().getNewsList();
        model.addAttribute("categoryList", new MysqlCommands().getCategoriesList());
        model.addAttribute("userwelcome", welcome);
        model.addAttribute("username",username);
        model.addAttribute("loginForm", new User());
        model.addAttribute("newsList",news);
        return model;
    }
}
