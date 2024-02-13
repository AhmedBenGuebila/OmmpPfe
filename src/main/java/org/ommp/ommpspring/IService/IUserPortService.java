package org.ommp.ommpspring.IService;

import org.ommp.ommpspring.entities.UserPort;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserPortService {
    UserPort saveUserPort(UserPort userPort);

    UserPort updateUserPort(UserPort userPort) throws ChangeSetPersister.NotFoundException;

    void deleteUserPort(Long userPortId) throws ChangeSetPersister.NotFoundException;

    UserPort getUserPortById(Long userPortId) throws ChangeSetPersister.NotFoundException;

    List<UserPort> getAllUserPorts();
}
