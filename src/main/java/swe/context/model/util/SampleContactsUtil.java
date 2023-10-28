package swe.context.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import swe.context.model.Contacts;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;


/**
 * Contains utility methods for working with sample {@link Contact}s.
 */
public class SampleContactsUtil {
    private static Contact[] getSampleContactsArray() {
        //TODO access these from a production data file. Help command text could reuse these valid data strings
        // Ensure test JSONs are also updated
        return new Contact[] {
            new Contact(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("friends"),
                getAlternateContactSet("Example@Alexyeoh")),
            new Contact(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("colleagues", "friends"),
                getAlternateContactSet("Example@berniceyu123")),
            new Contact(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("neighbours"),
                getAlternateContactSet("Example@Charlotte21")),
            new Contact(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("family"),
                getAlternateContactSet("Example@dl312")),
            new Contact(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("classmates"),
                getAlternateContactSet("Example@IrfanIbrahim")),
            new Contact(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Note("CS2103 tutorial mate."),
                getTagSet("colleagues"),
                getAlternateContactSet("Example@RoyBalakrishnan"))
        };
    }

    /**
     * Returns a {@link Set} of {@link Tag}s for the specified strings.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<AlternateContact> getAlternateContactSet(String... strings) {
        return Arrays.stream(strings)
                .map(AlternateContact::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a {@link Contacts} instance populated with sample {@link Contact}s.
     */
    public static Contacts getSampleContacts() {
        Contacts contacts = new Contacts();
        for (Contact contact : getSampleContactsArray()) {
            contacts.add(contact);
        }
        return contacts;
    }
}
