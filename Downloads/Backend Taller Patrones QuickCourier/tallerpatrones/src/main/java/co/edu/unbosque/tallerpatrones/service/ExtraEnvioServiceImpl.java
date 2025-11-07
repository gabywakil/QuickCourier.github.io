package co.edu.unbosque.tallerpatrones.service;

import co.edu.unbosque.tallerpatrones.dto.ExtraEnvioDTO;
import co.edu.unbosque.tallerpatrones.mapper.ExtraEnvioMapper;
import co.edu.unbosque.tallerpatrones.model.ExtraEnvio;
import co.edu.unbosque.tallerpatrones.repository.ExtraEnvioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ExtraEnvioServiceImpl implements ExtraEnvioService {

    private final ExtraEnvioRepository extraEnvioRepository;

    public ExtraEnvioServiceImpl(ExtraEnvioRepository extraEnvioRepository) {
        this.extraEnvioRepository = extraEnvioRepository;
    }

    @Override
    public List<ExtraEnvioDTO> listarExtrasActivos() {
        List<ExtraEnvio> extras = extraEnvioRepository.findByActivoTrue();
        return extras.stream()
                .map(ExtraEnvioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
