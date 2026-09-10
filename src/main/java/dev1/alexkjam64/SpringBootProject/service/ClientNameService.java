package dev1.alexkjam64.SpringBootProject.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev1.alexkjam64.SpringBootProject.repository.ClientName;
import dev1.alexkjam64.SpringBootProject.repository.ClientNameRepository;

@Service
public class ClientNameService {
    private final ClientNameRepository clientRepository;

    public ClientNameService(ClientNameRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientName retrieve(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        return clientRepository.getName(id);
    }

    public Map<Integer, ClientName> retrieveAllNames(List<Integer> ids){
        return clientRepository.getBatchNames(ids).stream().collect(Collectors.toMap(ClientName::id, name->name));
    }

    // Sanitizes data before adding to database
    public void create(ClientName request, int id) throws InvalidDataException{
        sanitizeData(request);
        
        // Assuming it past all the checks... call the repo to create
        clientRepository.addClient(request, id);
    }

    // Sanitizes data before updating database
    public void update(ClientName entity, int id) throws InvalidDataException, NoDataException{
        sanitizeData(entity);

        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.updateClient(entity, id);
    }

    public void delete(int id) throws NoDataException{
        // If data does not exist... blow up!
        checkDataExist(id);

        // If the data exist... update using the repo
        clientRepository.deleteClient(id);
    }

    protected void sanitizeData(ClientName data) throws InvalidDataException{
        // If first name and last name are null or empty... blow up!
        if(data.firstName() == null || data.firstName().trim().isEmpty()){
            throw new InvalidDataException("First name is null or empty!");
        }
        if(data.lastName() == null || data.lastName().trim().isEmpty()){
            throw new InvalidDataException("Last name is null or empty!");
        }

        // If any of the name attributes are longer than the db columns... blow up!
        if(data.firstName().length() > 20){
            throw new InvalidDataException("First name surpasses 20 characters!");
        }
        if(data.lastName().length() > 60){
            throw new InvalidDataException("Last name surpasses 60 characters!");
        }
        if(data.middleInit() != null && data.middleInit().length() > 1){
            throw new InvalidDataException("Middle initial surpasses 1 character!");
        }

        // If any of the names include special characters... blow up!
        if(data.firstName().matches(".*[^a-zA-Z0-9].*")){
            throw new InvalidDataException("First name includes special characters!");
        }
        if(data.lastName().matches(".*[^a-zA-Z0-9].*")){
            throw new InvalidDataException("Last name includes special characters!");
        }
        if(data.middleInit().matches(".*[^a-zA-Z0-9].*")){
            throw new InvalidDataException("Middle initial includes a special character!");
        }
    }

    protected void checkDataExist(int id) throws NoDataException{
        if(clientRepository.getName(id) == null){
            throw new NoDataException("Data does not exist!");
        }
    }
}
