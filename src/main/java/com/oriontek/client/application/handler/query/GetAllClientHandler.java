package com.oriontek.client.application.handler.query;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.query.GetAllClientUseCase;
import com.oriontek.client.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllClientHandler implements Handler<Pageable, Page<ClientDTO>> {

    private final GetAllClientUseCase getAllClientUseCase;

    public GetAllClientHandler(GetAllClientUseCase getAllClientUseCase) {
        this.getAllClientUseCase = getAllClientUseCase;
    }

    @Override
    public Page<ClientDTO> handle(Pageable pageable) {
        return getAllClientUseCase.execute(pageable);
    }
}
