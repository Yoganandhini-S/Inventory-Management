package com.example.InventorySystem.Controller;

import com.example.InventorySystem.model.InventoryItem;
//import com.example.InventorySystem.Controller.User;
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

    // LOGIN PAGE
    @GetMapping("/login")
    public String showLoginPage() {
        return "/WEB-INF/views/login"; // login.jsp
    }

    // LOGIN HANDLER (Database based)
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = userRepo.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/WEB-INF/views/home"; // Redirect to add page
        } else {
            return "redirect:/?error=true";
        }
    }

    // SIGNUP PAGE
    @GetMapping("/signup")
    public String showSignupPage() {
        return "/WEB-INF/views/signup";  // signup.jsp
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
        newUser.setPassword(password);  // 🔒 Consider hashing for production
        userRepo.save(newUser);

        session.setAttribute("user", username);
        return "redirect:/WEB-INF/views/";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate();
        return "redirect:/WEB-INF/views/";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        return "/WEB-INF/views/add"; // add.jsp
    }

    // SAVE NEW ITEM
    @PostMapping("/save")
    public String saveItem(@ModelAttribute InventoryItem item, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        repository.save(item);
        return "redirect:/WEB-INF/views/inventory";
    }

    // SHOW INVENTORY
    @GetMapping("/inventory")
    public String showInventory(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        List<InventoryItem> items = repository.findAll();
        model.addAttribute("items", items);
        return "/WEB-INF/views/inventory"; // inventory.jsp
    }

    // SHOW UPDATE FORM
    @GetMapping("/update")
    public String showUpdateForm(@RequestParam Long id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        InventoryItem item = repository.findById(id).orElse(null);
        model.addAttribute("item", item);
        return "/WEB-INF/views/update"; // update.jsp
    }

    // HANDLE UPDATE
    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute InventoryItem updatedItem, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        repository.save(updatedItem);
        return "redirect:/WEB-INF/views/inventory";
    }

    // DELETE ITEM
    @GetMapping("/delete")
    public String deleteItem(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/";
        repository.deleteById(id);
        return "redirect:/WEB-INF/views/inventory";
    }
    
    //@GetMapping("/logout")
    //public String logout(HttpSession session) {
        //String username = (String) session.getAttribute("user");
        //if (username != null) {
          //  User user = userRepo.findByUsername(username);
           // if (user != null) {
               // userRepo.delete(user); // ⚠️ deletes user permanently
           // }
       // }
        //session.invalidate();
        //return "redirect:/";
   // }
    
  

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/";

        List<InventoryItem> items = repository.findAll();
        int totalItems = items.size();
        int totalQuantity = items.stream().mapToInt(InventoryItem::getQuantity).sum();
        double totalValue = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        List<InventoryItem> recentItems = items.stream()
                .sorted((a, b) -> b.getId().compareTo(a.getId()))
                .limit(3)
                .toList();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("recentItems", recentItems);
        return "/WEB-INF/views/home";
    }
    
}
