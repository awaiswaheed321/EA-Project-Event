package edu.miu.cs544.awais.EventManagementService.shared;

import edu.miu.cs544.awais.EventManagementService.admin.domain.Admin;
import edu.miu.cs544.awais.EventManagementService.admin.AdminRepository;
import edu.miu.cs544.awais.EventManagementService.category.CategoryRepository;
import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.event.EventRepository;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.location.LocationRepository;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRepository;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Profile("data")
public class DataGenerationService implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final StaffRepository staffRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataGenerationService(AdminRepository adminRepository, CategoryRepository categoryRepository,
                                 LocationRepository locationRepository,
                                 StaffRepository staffRepository,
                                 EventRepository eventRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.staffRepository = staffRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        generateAdmins();
        generateCategories();
        generateLocations();
        generateStaff();
        generateEvents();
    }

    private void generateAdmins() {
        List<Admin> admins = List.of(new Admin("admin1", "admin1@example.com", passwordEncoder.encode("password_1234")),
                new Admin("admin2", "admin2@example.com", passwordEncoder.encode("password_1234")),
                new Admin("admin3", "admin3@example.com", passwordEncoder.encode("password_1234")));
        adminRepository.saveAll(admins);
    }

    private void generateCategories() {
        List<Category> categories = Arrays.asList(
                new Category("Tech Conference", "A conference for tech enthusiasts."),
                new Category("Music Festival", "A large music festival with various bands."),
                new Category("Art Exhibition", "An exhibition of local artists."),
                new Category("Food Expo", "An expo showcasing gourmet food and drinks."),
                new Category("Sports Tournament", "A regional sports competition.")
        );
        categoryRepository.saveAll(categories);
    }

    private void generateLocations() {
        List<Location> locations = Arrays.asList(
                new Location("Grand Hall", "123 Main St", "Springfield", "IL", "62701"),
                new Location("Downtown Arena", "456 Market St", "Chicago", "IL", "60602"),
                new Location("City Park", "789 Park Ave", "Denver", "CO", "80203"),
                new Location("Beachside Pavilion", "101 Ocean Blvd", "Miami", "FL", "33101"),
                new Location("Expo Center", "202 Expo Dr", "Los Angeles", "CA", "90001")
        );
        locationRepository.saveAll(locations);
    }

    private void generateStaff() {
        List<Staff> staffList = Arrays.asList(
                new Staff("username_1", "email_1@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.EVENT_PLANNER),
                new Staff("username_2", "email_2@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.LOGISTICS_MANAGER),
                new Staff("username_3", "email_3@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.CATERING_MANAGER),
                new Staff("username_4", "email_4@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.MARKETING_SPECIALIST),
                new Staff("username_5", "email_5@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.TECH_SUPPORT),
                new Staff("username_6", "email_6@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.REGISTRATION_COORDINATOR),
                new Staff("username_7", "email_7@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.EVENT_PLANNER),
                new Staff("username_8", "email_8@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.LOGISTICS_MANAGER),
                new Staff("username_9", "email_9@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.CATERING_MANAGER),
                new Staff("username_10", "email_10@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.MARKETING_SPECIALIST),
                new Staff("username_11", "email_11@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.TECH_SUPPORT),
                new Staff("username_12", "email_12@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.REGISTRATION_COORDINATOR),
                new Staff("username_13", "email_13@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.EVENT_PLANNER),
                new Staff("username_14", "email_14@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.LOGISTICS_MANAGER),
                new Staff("username_15", "email_15@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.CATERING_MANAGER),
                new Staff("username_16", "email_16@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.MARKETING_SPECIALIST),
                new Staff("username_17", "email_17@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.TECH_SUPPORT),
                new Staff("username_18", "email_18@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.REGISTRATION_COORDINATOR),
                new Staff("username_19", "email_19@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.EVENT_PLANNER),
                new Staff("username_20", "email_20@example.com", passwordEncoder.encode("password_1234"),
                        StaffRole.LOGISTICS_MANAGER)
        );

        staffRepository.saveAll(staffList);
    }

    private void generateEvents() {
        Random random = new Random();
        List<Category> categories = categoryRepository.findAll();
        List<Location> locations = locationRepository.findAll();
        List<Staff> staffMembers = staffRepository.findAll();

        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("Event #" + (i + 1));
            event.setDescription("Description for event #" + (i + 1));
            event.setDate(LocalDateTime.now().plusDays(random.nextInt(30)));
            event.setTotalSeats(200 + random.nextInt(100));
            event.setAvailableSeats(event.getTotalSeats());
            event.setCategory(categories.get(random.nextInt(categories.size())));
            event.setLocation(locations.get(random.nextInt(locations.size())));
            // Randomly assigning 3-5 staff members
            int numberOfStaff = 3 + random.nextInt(3); // Staff between 3 to 5
            for (int j = 0; j < numberOfStaff; j++) {
                event.getStaff().add(staffMembers.get(random.nextInt(staffMembers.size())));
            }
            eventRepository.save(event);
        }
    }
}
