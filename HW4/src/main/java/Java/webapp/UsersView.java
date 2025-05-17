package Java.webapp;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;

@PageTitle("Users")
@Route("users")
@AnonymousAllowed
public class UsersView extends VerticalLayout {
    private final UserRepository userRepository;
    private final Grid<User> userGrid = new Grid<>(User.class);

    public UsersView(UserRepository userRepository) {
        this.userRepository = userRepository;

        setSizeFull();
        configureGrid();
        add(userGrid);
        updateGrid();
    }

    private void configureGrid() {
        userGrid.setColumns("id", "username"); // Show only relevant fields
        userGrid.getColumnByKey("id").setHeader("ID");
        userGrid.getColumnByKey("username").setHeader("Username");
        userGrid.setSizeFull();
    }

    private void updateGrid() {
        List<User> users = userRepository.findAll();
        userGrid.setItems(users);
    }
}
