package com.jureczko.take.initializer;

import com.jureczko.take.model.Ingredient;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Client;
import com.jureczko.take.model.Recipe;
import com.jureczko.take.model.Order;
import com.jureczko.take.model.OrderDish;
import com.jureczko.take.repository.ClientRepository;
import com.jureczko.take.repository.DishRepository;
import com.jureczko.take.repository.IngredientRepository;
import com.jureczko.take.repository.OrderRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInitializer {

    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        if (clientRepository.count() == 0 && dishRepository.count() == 0 && ingredientRepository.count() == 0) {
            // === INGREDIENTS ===
            Ingredient tomato       = createIngredient("Tomato", 50);
            Ingredient cheese       = createIngredient("Cheese", 30);
            Ingredient dough        = createIngredient("Dough", 20);
            Ingredient ham          = createIngredient("Ham", 25);
            Ingredient mushroom     = createIngredient("Mushroom", 40);
            Ingredient olives       = createIngredient("Olives", 15);

            List<Ingredient> ingredients = ingredientRepository.saveAll(
                    List.of(tomato, cheese, dough, ham, mushroom, olives)
            );

            // === DISHES + RECIPES ===
            Dish margherita         = createDish("Pizza Margherita", 25.0);
            Dish capricciosa        = createDish("Pizza Capricciosa", 30.0);
            Dish funghi             = createDish("Pizza Funghi", 27.5);
            Dish quatroFormaggi     = createDish("Pizza Quattro Formaggi", 32.0);
            Dish veggi              = createDish("Pizza Vegetariana", 29.0);

            margherita.setRecipe(List.of(
                    createRecipe(margherita, tomato, 2, "pcs"),
                    createRecipe(margherita, cheese, 0.1, "kg"),
                    createRecipe(margherita, dough, 1, "piece")
            ));

            capricciosa.setRecipe(List.of(
                    createRecipe(capricciosa, tomato, 2, "pcs"),
                    createRecipe(capricciosa, cheese, 0.1, "kg"),
                    createRecipe(capricciosa, dough, 1, "piece"),
                    createRecipe(capricciosa, ham, 0.08, "kg"),
                    createRecipe(capricciosa, mushroom, 0.06, "kg")
            ));

            funghi.setRecipe(List.of(
                    createRecipe(funghi, tomato, 2, "pcs"),
                    createRecipe(funghi, cheese, 0.08, "kg"),
                    createRecipe(funghi, dough, 1, "piece"),
                    createRecipe(funghi, mushroom, 0.1, "kg")
            ));

            quatroFormaggi.setRecipe(List.of(
                    createRecipe(quatroFormaggi, cheese, 0.15, "kg"),
                    createRecipe(quatroFormaggi, dough, 1, "piece"),
                    createRecipe(quatroFormaggi, tomato, 1, "pcs")
            ));

            veggi.setRecipe(List.of(
                    createRecipe(veggi, dough, 1, "piece"),
                    createRecipe(veggi, tomato, 2, "pcs"),
                    createRecipe(veggi, mushroom, 0.05, "kg"),
                    createRecipe(veggi, olives, 0.03, "kg"),
                    createRecipe(veggi, cheese, 0.05, "kg")
            ));

            dishRepository.saveAll(List.of(margherita, capricciosa, funghi, quatroFormaggi, veggi));

            // === CLIENTS ===
            Client c1 = createClient("Anna", "Kowalska", "anna@example.com", LocalDate.of(1990, 1, 15), "123-456-789");
            Client c2 = createClient("Jan", "Nowak", "jan@example.com", LocalDate.of(1985, 5, 20), "234-567-890");
            Client c3 = createClient("Ola", "Wiśniewska", "ola@example.com", LocalDate.of(1993, 7, 3), "345-678-901");
            Client c4 = createClient("Marek", "Lewandowski", "marek@example.com", LocalDate.of(1988, 3, 12), "456-789-012");
            Client c5 = createClient("Kasia", "Zielińska", "kasia@example.com", LocalDate.of(1991, 11, 23), "567-890-123");

            clientRepository.saveAll(List.of(c1, c2, c3, c4, c5));

            // === ORDERS ===
            List<Order> orders = new ArrayList<>();

            orders.add(createOrder(c1, margherita, 2));
            orders.add(createOrder(c2, capricciosa, 1));
            orders.add(createOrder(c3, quatroFormaggi, 3));
            orders.add(createOrder(c4, funghi, 1));
            orders.add(createOrder(c4, veggi, 2));

            orderRepository.saveAll(orders);

            System.out.println("✅ Baza danych została wypełniona danymi testowymi.");
        }
    }

    private Order createOrder(Client client, Dish dish, int quantity) {
        Order order = new Order();
        order.setClient(client);
        order.setOrderDateTime(LocalDateTime.now());
        order.setTotalPrice(dish.getPrice() * quantity);

        OrderDish orderDish = createOrderDish(order, dish, quantity);
        order.setOrderDish(List.of(orderDish));

        return order;
    }

    private Ingredient createIngredient(String name, int stockStatus) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setStockStatus(stockStatus);
        return ingredient;
    }

    private Dish createDish(String name, double price) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setPrice(price);
        return dish;
    }

    private Recipe createRecipe(Dish dish, Ingredient ingredient, double quantity, String unit) {
        Recipe recipe = new Recipe();
        recipe.setDish(dish);
        recipe.setIngredient(ingredient);
        recipe.setQuantity(quantity);
        recipe.setUnit(unit);
        return recipe;
    }

    private Client createClient(String name, String lastName, String email, LocalDate birthday, String phoneNumber) {
        Client client = new Client();
        client.setName(name);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setBirthday(birthday);
        client.setPhoneNumber(phoneNumber);
        return client;
    }

    private OrderDish createOrderDish(Order order, Dish dish, int quantity) {
        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dish);
        orderDish.setQuantity(quantity);
        return orderDish;
    }
}