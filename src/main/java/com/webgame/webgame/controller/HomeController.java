package com.webgame.webgame.controller;

import com.webgame.webgame.model.Category;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.service.category.CategoryService;
import com.webgame.webgame.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    GameService gameService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/gameList/{pageNo}")
    public Map<String, Object> gameList(@PathVariable(value = "pageNo") int page) {
        int size = 8;
        List<Game> gameListTmp = new ArrayList<>();
        for (int i = 0; i <= page; i++) {
            Page<Game> gamePage = gameService.getGameList(i, size, "createDate");
            gameListTmp.addAll(gamePage.getContent());
        }

        Map<String, Object> response = new HashMap<>();

        response.put("gameList", gameListTmp);
        response.put("currentPage", page);

        return response;
    }

    // API lấy danh sách category
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategoryList();
    }

    @GetMapping("/search")
    public List<Game> searchGame(
            @RequestParam(value = "searchInput") String searchInput) {
        return gameService.getGameSearchInput(searchInput);
    }

    @GetMapping("/category/{id}")
    public List<Game> getGamesByCategory(@PathVariable("id") Long categoryId) {
        // Lấy thể loại từ ID
        Category category = categoryService.getCategoryById(categoryId);

        return gameService.getGameListCategory(category); // Trả về view hiển thị danh sách game
    }

}
