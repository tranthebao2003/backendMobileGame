package com.webgame.webgame.service.game;

import com.webgame.webgame.dto.gameDto.GameFormDto;
import com.webgame.webgame.model.Category;
import com.webgame.webgame.model.CategoryGame;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.repository.CategoryGameRepository;
import com.webgame.webgame.repository.CategoryRepository;
import com.webgame.webgame.repository.GameRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class GameServiceImp implements GameService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryGameRepository categoryGameRepository;

    @Override
    public Page<Game> getGameList(int page, int size, String sortField) {
        // object sort này sắp xếp theo param sortField, theo giảm dần
        Sort sort = Sort.by(sortField).descending();
        return gameRepository.findAll(PageRequest.of(page, size, sort));
    }

    @Override
    public List<Game> getGameSearchInput(String searchInput) {
        return gameRepository.findGamesByGameNameContainingIgnoreCase(searchInput);
    }

    @Override
    public List<Game> getGameListCategory(Category category) {
        return gameRepository.findByCategoryGames_Category(category);
    }

    @Override
    public Page<Object[]> findGamesAndQuantityCategory(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return gameRepository.findGamesAndQuantityCategory(pageable);
    }


    @Override
    public void deleteGameById(Long id) {
        this.categoryGameRepository.deleteByGameId(id);
        this.gameRepository.deleteById(id);
    }

    @Override
    public Game getGameById(Long id) {
        Optional<Game> optional = gameRepository.findById(id);
        Game game;
        if (optional.isPresent()) {
            game = optional.get();
        } else {
//            lỗi này đc ném ra thì ko chạy tiếp phần dưới nữa
            throw new RuntimeException(" Game not found for id :: " + id);
        }
        return game;
    }

    public List<CategoryGame> getCategoryGameFromDto(GameFormDto gameFormDto, Game game) {
        // Xử lý CategoryGames
        List<CategoryGame> categoryGames = new ArrayList<>();
        for (Long categoryId : gameFormDto.getCategoryIds()) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                CategoryGame categoryGame = new CategoryGame(game, category);
                categoryGames.add(categoryGame);
            } else {
                System.out.println("Category ID " + categoryId + " not found!");
            }
        }
        return categoryGames;
    }

    // Chuyển dữ liệu từ GameFromDto mà nó đã nhận trước đó vào cho game
    public Game getGameFromDto(Game game, GameFormDto gameFormDto) throws IOException {
        game.setGameName(gameFormDto.getGameName());
        game.setDescription(gameFormDto.getDescription());
        game.setPrice(gameFormDto.getPrice());

        // Lưu file ảnh vào thư mục
        String fileName = UUID.randomUUID() + "_" + gameFormDto.getGameImg().getOriginalFilename();
        Path filePath = Paths.get("src/main/java/com/webgame/webgame/uploadImgGame/", fileName);
        Files.copy(gameFormDto.getGameImg().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        game.setGameImg(fileName);
        return game;
    }

    // add game
    @Override
    public void saveGame(GameFormDto gameFormDto) throws IOException {
        Game game = new Game();
        game = getGameFromDto(game, gameFormDto);
        game.setCategoryGames(getCategoryGameFromDto(gameFormDto, game));

        // Lưu game vào database
        gameRepository.save(game);
    }

    // update game
    @Override
    public void updateGame(Long id, GameFormDto gameFormDto) throws IOException {
        // nhận game cần update
        Game gameExist = getGameById(id);
        gameExist = getGameFromDto(gameExist, gameFormDto);

        // Xóa các liên kết cũ giữa category và game bên trong CategoryGame
        this.categoryGameRepository.deleteByGameId(id);
        // Clear context to avoid những thực thể cũ, dòng dưới rất quan trong
        entityManager.clear();

        // Lưu lại thông tin đã cập nhật
        categoryGameRepository.saveAll(getCategoryGameFromDto(gameFormDto, gameExist));

        // saveAll Lưu danh sách liên kết mới vào bảng CategoryGame
        gameRepository.save(gameExist);
    }

    @Override
    public List<Category> findCategoriesByGameId(Long gameId) {
        return gameRepository.findCategoriesByGameId(gameId);
    }
}
