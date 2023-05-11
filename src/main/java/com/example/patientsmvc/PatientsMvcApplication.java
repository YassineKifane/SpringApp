package com.example.patientsmvc;

import com.example.patientsmvc.entities.Patient;
import com.example.patientsmvc.repositories.PatientRepository;
import com.example.patientsmvc.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Yassine",new Date(),false,4));
            patientRepository.save(new Patient(null,"Simo",new Date(),true,44));
            patientRepository.save(new Patient(null,"Ali",new Date(),false,24));
            patientRepository.save(new Patient(null,"Amine",new Date(),true,11));

        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder= passwordEncoder();
        return args -> {

            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user3");
            if (u1==null) jdbcUserDetailsManager.createUser(User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER").build());

            UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user4");
            if (u2==null) jdbcUserDetailsManager.createUser(User.withUsername("user4").password(passwordEncoder.encode("1234")).roles("USER").build());

            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (u3==null) jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("admin")).roles("USER","ADMIN").build());

            UserDetails u4= jdbcUserDetailsManager.loadUserByUsername("admin");
            if (u3==null) jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN").build());
        };
    }


    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user10","1234","user1@gmail.com","1234");
            accountService.addNewUser("user20","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin30","admin","admin@gmail.com","admin");

            accountService.addRoleToUser("user10","USER");
            accountService.addRoleToUser("user20","USER");
            accountService.addRoleToUser("admin30","USER");
            accountService.addRoleToUser("admin30","ADMIN");
        };
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
