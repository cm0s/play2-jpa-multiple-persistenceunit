package controllers;

import models.Company;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    //This method run with the otherPersistenceUnit
    @Transactional(value="other")
    public static Result test1() {

        JPA.em().persist(new Company("MyCompany"));

        //Transaction is run with the "defaultPersistenceUnit"
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                JPA.em().persist(new User("Bobby"));
            }
        });

        return ok();
    }

    //This action run with the otherPersistenceUnit
    @Transactional
    public static Result test2() {
        JPA.em().persist(new User("Ryan"));

        try {
            JPA.withTransaction("other", false, new play.libs.F.Function0<Void>() {
                public Void apply() throws Throwable {
                    JPA.em().persist(new Company("YourCompany"));
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        return ok();
    }
}
