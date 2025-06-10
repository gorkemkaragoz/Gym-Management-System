package com.gymforhealthy.gms;

import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.repository.RoleRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.gymforhealthy.gms.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GymManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RoleRepository roleRepository,
							 UserRepository userRepository,
							 PasswordEncoder passwordEncoder) {
		return args -> {
			// Roller yoksa ekle
			if (roleRepository.count() == 0) {
				roleRepository.saveAll(List.of(
						new Role(null, "ADMIN", new ArrayList<>()),
						new Role(null, "TRAINER", new ArrayList<>()),
						new Role(null, "MEMBER", new ArrayList<>())
				));
			}

			// Kullanıcı yoksa ekle
			if (userRepository.count() <= 3) {
				Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
				Role trainerRole = roleRepository.findByName("TRAINER").orElseThrow();
				Role memberRole = roleRepository.findByName("MEMBER").orElseThrow();

				userRepository.saveAll(List.of(
						new User("admin@example.com", passwordEncoder.encode("123456"), "Admin", "Admin", "11111111111", adminRole),
						new User("trainer@example.com", passwordEncoder.encode("123456"), "Trainer", "Trainer", "22222222222", trainerRole),
						new User("member@example.com", passwordEncoder.encode("123456"), "Member", "Member", "33333333333", memberRole),
						new User("gymapp.managementproject@gmail.com", passwordEncoder.encode("123456"), "Mail", "Test", "88888888888", memberRole)
				));
			}
		};
	}

}
