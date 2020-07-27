package db.seeder;

import db.DB;
import models.Product;
import models.Review;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReviewSeeder {

    public static void Seed(DB db) {
        List<User> users = User.getAll();
        List<Product> products = Product.getAll();
        Random rand = new Random();

        List<String> comments = new ArrayList<String>() {
            {
                add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
                add("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit");
                add("Sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.");
                add("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"");
                add("Te has veritus sapientem, audire minimum scaevola vis ex," +
                        " cum nonumy denique liberavisse ut. Elit augue definiebas ius ea, per omnium persequeris mediocritatem et. His ea posse impetus definitionem, cibo expetendis te vix. Zril deseruisse eam te. Eu eam soluta qualisque. Eos alii ferri splendide cu.");
            }
        };

        for (int i = 0; i < 1000; i++)
            new Review(comments.get(rand.nextInt(comments.size())),
                    rand.nextInt(5) + 1,
                    users.get(rand.nextInt(users.size())).getId(),
                    products.get(rand.nextInt(products.size())).getId()).save();
    }
}
