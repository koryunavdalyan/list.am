package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Item;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @GetMapping("/items")
    public String itemsPage(ModelMap modelMap) {
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "items";
    }

    @GetMapping("/items/{id}")
    public String singleItemPage(@PathVariable("id") int id,
                                 ModelMap modelMap) {
        Optional<Item> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            Item item = byId.get();
            modelMap.addAttribute("item", item);
            return "singleItem";
        } else {
            return "redirect:/items";
        }
    }

        @GetMapping("/items/add")
        public String ItemsAddPage (ModelMap modelMap){
            List<Category> all = categoryRepository.findAll();
            modelMap.addAttribute("categories", all);
            return "addItem";
        }

        @PostMapping("/items/add")
        public String ItemsAdd (@ModelAttribute Item item){
            itemRepository.save(item);
            return "redirect:/items";
        }

        @GetMapping("/items/remove")
        public String removeItem ( @RequestParam("id") int id){
            itemRepository.deleteById(id);
            return "redirect:/items";
        }
    }
