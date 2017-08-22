package store.utils;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MapperHandler {

    private final ModelMapper mapper;

    public <T,S> S transformTo(T what, Class<S> toWhat){
        return mapper.map(what, toWhat);
    }
}
