package daoClasses;

import dao.GenericDAO;
import entity.Trainer;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDAO extends GenericDAO<Trainer, Long> {}
