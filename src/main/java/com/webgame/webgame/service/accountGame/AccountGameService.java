package com.webgame.webgame.service.accountGame;

import com.webgame.webgame.dto.gameDto.GameSaleDto;
import org.springframework.data.domain.Page;

public interface AccountGameService {
    // ở đây mình ko cần sắp xếp giảm dần theo tổng
    // sp bán được nữa vì mình đã sắp xếp trong câu query rồi
    Page<GameSaleDto> totalAccountGameSold(int page, int size);
}
