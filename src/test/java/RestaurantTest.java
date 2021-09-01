import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    //    TestMenu is used as the menu used by user to select items
    List<Item> TestMenu = new ArrayList<Item>();
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test
    public boolean is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        RestaurantCafe();

        return true;



    }

    @Test
    public boolean is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
            return true;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        RestaurantCafe();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    private void RestaurantCafe() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        RestaurantCafe();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        RestaurantCafe();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void order_value_must_sum_up_when_multiple_items_are_selected (){
        createTestRestaurant();

        TestMenu = restaurant.getMenu();

        assertEquals(388,restaurant.getOrderValue(dummyMenu));

    }


    @Test
    public void order_value_must_reduce_when_item_is_removed (){
        createTestRestaurant();

        TestMenu = restaurant.getMenu();
        int total = restaurant.getOrderValue(dummyMenu);
        int priceOfItemRemoved = dummyMenu.get(1).getPrice();
        dummyMenu.remove(1);



        assertEquals(total - priceOfItemRemoved,restaurant.getOrderValue(dummyMenu));

    }
}

