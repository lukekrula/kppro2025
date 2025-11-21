package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Item;
import cz.uhk.kppro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MainController {

    private ItemService itemService;

    @Autowired
    public MainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", itemService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Item());
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("item", itemService.get(id));
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item) {
        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Item item = itemService.get(id);
        if(item != null) {
            model.addAttribute("item", item);
            return "detail";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Item item = itemService.get(id);
        if(item != null) {
            itemService.delete(id);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/detail/{id}")
    public String detail(@PathVariable int id,
                         @RequestParam(defaultValue = "false") boolean done) {
        Item item = itemService.get(id);
        if(item != null) {
            item.setDone(done);
            itemService.save(item);
            return "redirect:/detail/" + String.valueOf(id);
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "nečum";
    }
}
