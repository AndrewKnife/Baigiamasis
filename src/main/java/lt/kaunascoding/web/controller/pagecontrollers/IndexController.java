package lt.kaunascoding.web.controller.pagecontrollers;

import lt.kaunascoding.web.controller.userControl.UserController;
import lt.kaunascoding.web.model.NewsRow;
import lt.kaunascoding.web.model.UserSession;
import lt.kaunascoding.web.model.mysql.MysqlCommands;
import lt.kaunascoding.web.model.mysql.classes.Category;
import lt.kaunascoding.web.model.mysql.classes.News;
import lt.kaunascoding.web.model.mysql.classes.User;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ImportAutoConfiguration
@Controller
public class IndexController {
    @GetMapping(value = "/")
    String index(Model model, @RequestParam(value = "category", required = false, defaultValue = "") String category){
        model = getPageModel(model, category);
        return "index";
    }

    @PostMapping(value = "/")
    public String login(@ModelAttribute("loginForm") User vartotojas, Model model) {
        UserController controller = new UserController();
        controller.canLogin(vartotojas);
        model = getPageModel(model, "");
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout( Model model) {
        new UserSession(new User());
        model = getPageModel(model, "");
        return "index";
    }


    private Model getPageModel(Model model, String category){
        String welcome = "";
        String username = "";
        List<News> newsall;
        List<News> newsHot =new ArrayList<>();
        List<News> newsPopular;
        List<News> newsNew = new ArrayList<>();
        List<News> news = new ArrayList<>();
        List<List<List<News>>> sorted = new ArrayList<>();
        List<NewsRow> newsByCategory = new ArrayList<>();
        boolean iscategory = false;
        if(UserSession.getUser() != null && UserSession.getUser().getNickname() != null){
            welcome = "Sveiki "+UserSession.getUser().getName();
            username = UserSession.getUser().getNickname();
        }
        if(category != null && !category.equals("")){
            newsall = new MysqlCommands().getNewsByCategory(category, 0);
            newsHot = getHotNews(newsall);
            newsPopular = new MysqlCommands().getPopularNews(category);
            iscategory = true;
            try {
                sorted = getNewsByRows(newsall, 2, 3,40);
                newsNew.add(newsall.get(0));
                newsNew.add(newsall.get(1));
                newsNew.add(newsall.get(2));
            }catch (Exception e){

            }
        }else{
            newsall = new MysqlCommands().getNewsList();
            newsHot = getHotNews(newsall);
            newsPopular = new MysqlCommands().getPopularNews("");
            sorted = getNewsByRows(newsall,2,3,4);
            newsNew.add(newsall.get(0));
            newsNew.add(newsall.get(1));
            newsNew.add(newsall.get(2));
            newsByCategory = getCategorieRows(new MysqlCommands().getCategoriesList());
        }

        model.addAttribute("categoryName",category);
        model.addAttribute("newsByCategory", newsByCategory);
        model.addAttribute("iscategory",iscategory);
        model.addAttribute("categoryList", new MysqlCommands().getCategoriesList());
        model.addAttribute("userwelcome", welcome);
        model.addAttribute("username",username);
        model.addAttribute("loginForm", new User());
        model.addAttribute("newsListHot",newsHot);
        model.addAttribute("newsListPopular",newsPopular);
        model.addAttribute("newsListNew", newsNew);
        model.addAttribute("complicated",sorted);
        return model;
    }

    private List<News> getHotNews(List<News> listas){
        List<News> hot = new ArrayList<>();
        int size = 0;

        try{
        for(int i = 0; i<listas.size();i++){
            if(listas.get(i).isHot()) {
                hot.add(listas.get(i));
                size++;
            }
            if(size==3){
                break;
            }
        }
        }catch (Exception e){

        }
        return hot;
    }

    private List<List<List<News>>> getNewsByRows(List<News> listas, int collumns, int from,int rowcount){

        List<List<List<News>>> podu = new ArrayList<>();
        List<List<News>> listuListas = new ArrayList<>();
        List<News> hot = new ArrayList<>();
        int sk = 0;
        int skai = 0;
        try{
        for(int i = from; i< listas.size();i++){
             hot.add(listas.get(i));
             skai++;
             sk++;
             if(sk == collumns){
                 listuListas.add(hot);
                 sk=0;
                 hot= new ArrayList<>();
             }
            if(listuListas.size() == 2){
                podu.add(listuListas);
                listuListas = new ArrayList<>();
            }
             if(rowcount != 0){
                 if(skai == rowcount*collumns){
                     break;
                 }
             }else{

             }


        }
        }catch (Exception e){

        }
        return podu;
    }


    private List<List<News>> getNewsRows(List<News> listas, int collumns, int from,int rowcount){

        List<List<News>> listuListas = new ArrayList<>();
        List<News> hot = new ArrayList<>();
        int sk = 0;
        int skai = 0;
        try{
            for(int i = from; i< listas.size();i++){
                hot.add(listas.get(i));
                skai++;
                sk++;
                if(sk == collumns){
                    listuListas.add(hot);
                    sk=0;
                    hot= new ArrayList<>();
                }
                if(rowcount != 0){
                    if(skai == rowcount*collumns){
                        break;
                    }
                }else{

                }
            }
        }catch (Exception e){

        }
        return listuListas;
    }

    private List<NewsRow> getCategorieRows(List<Category> kategorijos){
        NewsRow eile;
        List<NewsRow> visi = new ArrayList<>();
        List<News> bignaujienos = new ArrayList<>();
        List<News> visosnaujienos;
        List<List<News>> eilutes;
        List<List<News>> temp;
        List<List<List<News>>> podvi = new ArrayList<>();
        for(Category kategorija : kategorijos){
            temp = new ArrayList<>();
            visosnaujienos = new MysqlCommands().getNewsByCategory(kategorija.getName(),11);
            bignaujienos.add(visosnaujienos.get(0));
            bignaujienos.add(visosnaujienos.get(1));
            bignaujienos.add(visosnaujienos.get(2));
            eilutes = getNewsRows(visosnaujienos,2,3,4);
            for (int i = 0; i< eilutes.size(); i++){
                temp.add(eilutes.get(i));
                if(temp.size()==2){
                    podvi.add(temp);
                    temp = new ArrayList<>();
                }
            }
            eile = new NewsRow(kategorija, bignaujienos, podvi);
            visi.add(eile);
            bignaujienos = new ArrayList<>();
            podvi = new ArrayList<>();

        }
        return visi;
    }

}
