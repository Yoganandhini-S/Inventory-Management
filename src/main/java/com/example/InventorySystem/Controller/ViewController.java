package com.example.InventorySystem.Controller;

import com.example.InventorySystem.model.InventoryItem;
import com.example.InventorySystem.model.User;
import com.example.InventorySystem.repository.InventoryRepository;
import com.example.InventorySystem.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private UserRepository userRepo;

    // Redirect root to login
    @GetMapping("/")
    public String loginRedirect() {
        return "redirect:/login";
    }

    // LOGIN PAGE (GET)
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // login.jsp
    }

    // LOGIN HANDLER (POST)
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error=true";
        }
    }

    // SIGNUP PAGE
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";  // signup.jsp
    }

    // SIGNUP HANDLER
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {
        if (userRepo.findByUsername(username) != null) {
            return "redirect:/signup?error=exists";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);  // 🔒 Hash in production
        userRepo.save(newUser);

        session.setAttribute("user", username);
        return "redirect:/dashboard";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";

        List<InventoryItem> items = repository.findAll();
        model.addAttribute("totalItems", items.size());
        model.addAttribute("totalQuantity", items.stream().mapToInt(InventoryItem::getQuantity).sum());
        model.addAttribute("totalValue", items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());
        model.addAttribute("recentItems", items.stream().sorted((a, b) -> b.getId().compareTo(a.getId())).limit(3).toList());

        return "home";  // home.jsp
    }

    // SHOW INVENTORY
    @GetMapping("/inventory")
    public String showInventory(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";

        model.addAttribute("items", repository.findAll());
        return "inventory";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "add";
    }

    // SAVE NEW ITEM
    @PostMapping("/save")
    public String saveItem(@ModelAttribute InventoryItem item, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        repository.save(item);
        return "redirect:/inventory";
    }

    // SHOW UPDATE FORM
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam Long id, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";

        InventoryItem item = repository.findById(id).orElse(null);
        model.addAttribute("item", item);
        return "update";
    }

    // HANDLE UPDATE
    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute InventoryItem updatedItem, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        repository.save(updatedItem);
        return "redirect:/inventory";
    }

    // DELETE ITEM
    @GetMapping("/delete")
    public String deleteItem(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        repository.deleteById(id);
        return "redirect:/inventory";
    }
}
