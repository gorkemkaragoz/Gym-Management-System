package com.gymforhealthy.gms;

import com.gymforhealthy.gms.entity.User;
import com.gymforhealthy.gms.entity.Role;
import com.gymforhealthy.gms.entity.MembershipPackage;
import com.gymforhealthy.gms.repository.RoleRepository;
import com.gymforhealthy.gms.repository.UserRepository;
import com.gymforhealthy.gms.repository.MembershipPackageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
							 MembershipPackageRepository membershipPackageRepository,
							 PasswordEncoder passwordEncoder) {
		return args -> {
			// Roller yoksa ekle
			if (roleRepository.count() == 0) {
				roleRepository.saveAll(List.of(
						new Role(null, "ADMIN", new ArrayList<>()),
						new Role(null, "TRAINER", new ArrayList<>()),
						new Role(null, "MEMBER", new ArrayList<>())
				));
				System.out.println("Default roles added.");
			}

			// Paketler yoksa ekle
			if (membershipPackageRepository.findByName("Full Package").isEmpty()) {
				MembershipPackage fullPackage = new MembershipPackage();
				fullPackage.setName("Full Package");
				fullPackage.setPackageTotalHour(99999);
				fullPackage.setIsUnlimited(true);
				membershipPackageRepository.save(fullPackage);
				System.out.println("Full Package added to database.");
			} else {
				System.out.println("Full Package already exists in database.");
			}

			if (membershipPackageRepository.findByName("Weekly Package").isEmpty()) {
				MembershipPackage weeklyPackage = new MembershipPackage();
				weeklyPackage.setName("Weekly Package");
				weeklyPackage.setPackageTotalHour(20);
				weeklyPackage.setIsUnlimited(false);
				membershipPackageRepository.save(weeklyPackage);
				System.out.println("Weekly Package added to database.");
			} else {
				System.out.println("Weekly Package already exists in database.");
			}

			Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("ADMIN role not found!"));
			Role trainerRole = roleRepository.findByName("TRAINER").orElseThrow(() -> new RuntimeException("TRAINER role not found!"));
			Role memberRole = roleRepository.findByName("MEMBER").orElseThrow(() -> new RuntimeException("MEMBER role not found!"));

			// Admin kullanıcısını kontrol et ve ekle
			if (userRepository.findByEmail("admin@example.com").isEmpty()) {
				userRepository.save(new User("admin@example.com", passwordEncoder.encode("123456"), "Admin", "Admin", "11111111111", adminRole));
				System.out.println("Default admin user added.");
			} else {
				System.out.println("Default admin user already exists.");
			}

			// Trainer kullanıcısını kontrol et ve ekle
			if (userRepository.findByEmail("trainer@example.com").isEmpty()) {
				userRepository.save(new User("trainer@example.com", passwordEncoder.encode("123456"), "Trainer", "Trainer", "22222222222", trainerRole));
				System.out.println("Default trainer user added.");
			} else {
				System.out.println("Default trainer user already exists.");
			}

			if (userRepository.findByEmail("member@example.com").isEmpty()) {
				userRepository.save(new User("member@example.com", passwordEncoder.encode("123456"), "Member", "Member", "33333333333", memberRole));
				System.out.println("Default member user added.");
			} else {
				System.out.println("Default member user already exists.");
			}
		};
	}
}
