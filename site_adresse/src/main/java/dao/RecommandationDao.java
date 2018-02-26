package dao;

import entities.Recommandation;

import java.util.List;

public interface RecommandationDao {

    List<Recommandation> listRecommandation();
    void addRecommandation(Recommandation recommandation);
    void deleteRecommandation(int id);
}
