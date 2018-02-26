package dao;

import entities.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> listArticle();
    void likeArticle(Integer id);
    void dislikeArticle(Integer id);
    Article getArticle(Integer id);
}
