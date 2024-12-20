package edu.miu.cs544.awais.EventManagementService.shared;

import edu.miu.cs544.awais.EventManagementService.admin.AdminRepository;
import edu.miu.cs544.awais.EventManagementService.admin.domain.Admin;
import edu.miu.cs544.awais.EventManagementService.category.CategoryRepository;
import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.customer.CustomerRepository;
import edu.miu.cs544.awais.EventManagementService.customer.domain.Customer;
import edu.miu.cs544.awais.EventManagementService.event.EventRepository;
import edu.miu.cs544.awais.EventManagementService.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.location.LocationRepository;
import edu.miu.cs544.awais.EventManagementService.location.domain.Location;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRepository;
import edu.miu.cs544.awais.EventManagementService.staff.StaffRole;
import edu.miu.cs544.awais.EventManagementService.staff.domain.Staff;
import edu.miu.cs544.awais.EventManagementService.ticket.PaymentMethod;
import edu.miu.cs544.awais.EventManagementService.ticket.TicketRepository;
import edu.miu.cs544.awais.EventManagementService.ticket.domain.Ticket;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Profile("data")
public class DataGenerationService implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataGenerationService.class);

    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final StaffRepository staffRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Autowired
    public DataGenerationService(AdminRepository adminRepository, CategoryRepository categoryRepository,
                                 LocationRepository locationRepository,
                                 StaffRepository staffRepository,
                                 EventRepository eventRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.staffRepository = staffRepository;
        this.eventRepository = eventRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        LOGGER.info("Starting Data Generation..");
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                LOGGER.info("\n\nData Generation Complete.\n");
            }
        });
        generateAdmins();
        generateCategories();
        generateLocations();
        generateStaff();
        generateEvents();
        generateCustomersAndTickets();
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
            event.setTicketPrice(Math.round((50 + random.nextDouble() * 150) * 100.0) / 100.0);
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

    protected void generateCustomersAndTickets() {
        Random random = new Random();
        List<Event> events = eventRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        // Generate 10 customers
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(
                    "customer" + (i + 1),
                    "customer" + (i + 1) + "@example.com",
                    "password" + (i + 1),
                    "123-456-789" + i,
                    "Street " + (i + 1),
                    "City" + (i + 1),
                    "State" + (i + 1),
                    "12345"
            );
            customers.add(customer);
        }
        customerRepository.saveAll(customers);
        for (int i = 0; i < 20; i++) {
            Event event = events.get(random.nextInt(events.size())); // Randomly select an event
            int quantity = 1 + random.nextInt(5); // Random quantity between 1 and 5
            if (event.getAvailableSeats() >= quantity) {
                Customer customer = customers.get(random.nextInt(customers.size()));
                Ticket ticket = new Ticket(quantity, event, PaymentMethod.CREDIT_CARD);
                customer.addTicket(ticket);
                event.setAvailableSeats(event.getAvailableSeats() - quantity);
            }
        }
        customerRepository.saveAll(customers);
        eventRepository.saveAll(events);
    }


}
