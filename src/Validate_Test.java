package src;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Validate_Test {

    // Test the happy path for zip code validation
    @Test
    void testZipHappy() {
        assertTrue(Validate.zip("17701"));
    }

    // Test abuse cases for zip validation
    @Test
    void testZipBad() throws IOException {
        String[] invalid_zips = {"16", "aaaaa", "dfasdf", "111111", "1111", "-111", "asdff"};  // Minors
        for (String zips : invalid_zips) {
            assertFalse(Validate.zip(zips));
        }
    }

    // Test the happy path for age validation (minor check)
    @Test
    void testMinorHappy() {
        int[] ages = {16, 17, 1, 4, 6, 2, 0};  // Minors
        for (int age : ages) {
            assertTrue(Validate.minor(age));
        }
    }

    // Test abuse cases for age validation
    @Test
    void testMinorAbuse() {
        Object[] ages = {18, 20, 65, -9}; 

        for (Object age : ages) {
            // Check if age is an instance of Integer or Double
            if (age instanceof Double) {
                // Round the double value to the nearest integer and cast it to int
                System.out.println(Math.round((Double) age));  // This returns long, so it's fine.
            } else if (age instanceof Integer) {
                // Just print the integer value if it's already an integer
                System.out.println(age);
            }
            
            // You can now apply your assertion here
            assertFalse(Validate.minor((int) age));  // Uncomment this line if needed
        }
    }

    // Test the happy path for email validation
    @Test
    void testEmailHappy() {
        String[] validEmails = {
            "test@example.com", "name.surname@domain.co", "user_123@subdomain.domain.com"
        };
        for (String email : validEmails) {
            assertTrue(Validate.email(email));
        }
    }

    // Test abuse cases for email validation
    @Test
    void testEmailAbuse() throws IOException {
        String[] invalidEmails = {
            "plainaddress", "@no.domain", "user@com", "user@.com", "user@domain..com", "user@domain.com.", 
            "user@@domain.com", "", " ", "user@domain.c", "user@domain.123", "user@-domain.com", "user@domain-.com", 
            "user@doma_in.com", "user@domain.c..com", "user@.domain.com", "user@domain_com.com", "user@domain.com..com"
        };
        for (String email : invalidEmails) {
            assertFalse(Validate.email(email));
        }

        try (BufferedReader br = new BufferedReader(new FileReader("blns.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                assertFalse(Validate.email(line.trim()));
            }
        }
    }

    // Test the happy path for latitude validation
    @Test
    void testLatitudeHappy() {
        double[] validLatitudes = {-90, 0, 45, 90, 89.9};
        for (double lat : validLatitudes) {
            assertTrue(Validate.isLat(lat));
        }
    }

    // Test abuse cases for latitude validation
    @Test
    void testLatitudeAbuse() {
        double[] invalidLatitudes = {-91, 91, 300, -90.4, 90.1};
        for (double lat : invalidLatitudes) {
            assertFalse(Validate.isLat(lat));
        }
    }

    // Test the happy path for longitude validation
    @Test
    void testLongitudeHappy() {
        double[] validLongitudes = {-180, 0, 45, 180, 179.9};
        for (double lng : validLongitudes) {
            assertTrue(Validate.isLng(lng));
        }
    }

    // Test abuse cases for longitude validation
    @Test
    void testLongitudeAbuse() {
        double[] invalidLongitudes = {-181, 181, 180.1};
        for (double lng : invalidLongitudes) {
            assertFalse(Validate.isLng(lng));
        }
    }

    // Test the happy path for domain validation
    @Test
    void testDomainHappy() {
        String[] validDomains = {
            "example.com", "mywebsite.org", "subdomain.example.net", "name123.com", "valid-domain.co.uk", "domain123.io",
            "company-name.com", "newsite.info", "website.xyz", "example1234.com", "my-cool-site.biz", "RomansInSussex.co.uk"
        };
        for (String domain : validDomains) {
            assertTrue(Validate.isDomain(domain));
        }
    }

    // Test abuse cases for domain validation
    @Test
    void testDomainAbuse() throws IOException {
        String[] invalidDomains = {
            "example domain.com", "invalid domain name.net", "hello world.org", "example@domain.com", "my_domain.com", 
            "user#name.com", "-example.com", "example-.com", "-domain-.org", "example..com", "my...website.org", 
            "site....com", "a".repeat(255) + ".com", "longlonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglong.com"
        };
        for (String domain : invalidDomains) {
            assertFalse(Validate.isDomain(domain));
        }
    }

    // Test the happy path for URL validation
    @Test
    void testUrlHappy() {
        String[] validUrls = {
            "http://example.com", "https://secure-site.com", "http://www.domain.org/path", "https://subdomain.example.com", 
            "http://example.com:8080", "https://example.com?query=value", "https://example.net/path/to/resource?key=value#section",
            "https://www.example.co.uk", "https://api.example.com/v1/endpoint", "https://www.example.net", "http://example.org"
        };
        for (String url : validUrls) {
            assertTrue(Validate.isUrl(url));
        }
    }

    // Test abuse cases for URL validation
    @Test
    void testUrlAbuse() throws IOException {
        String[] invalidUrls = {
            "plainaddress", "http://", "www.example.net", "ftp://example.com", "http://example .org", "http://example.com:abcd",
            "http://example@com", "aaaa"
        };
        for (String url : invalidUrls) {
            assertFalse(Validate.isUrl(url));
        }
    }

    // Test grade validation (happy path)
    @Test
    void testGradeHappy() {
        assertEquals("F", Validate.grade(0));
        assertEquals("F", Validate.grade(59));
        assertEquals("D", Validate.grade(65));
        assertEquals("C", Validate.grade(75));
        assertEquals("B", Validate.grade(85));
        assertEquals("A", Validate.grade(95));
    }

    // Test abuse cases for grade validation
    @Test
    void testGradeAbuse() {
        double[] invalidScores = {-11, -1.33, 3};
        for (double score : invalidScores) {
            assertEquals("F", Validate.grade(score));
        }
    }

    // Test sanitize function
    @Test
    void testSanitize() {
        String sqlInput = "SELECT * FROM users WHERE username = 'admin' -- OR 1=1";
        String sanitized = Validate.sanitize(sqlInput);
        assertFalse(sanitized.contains("ADMIN"));
        assertFalse(sanitized.contains("OR"));
    }

    // Test strip null function
    @Test
    void testStripNull() {
        String inputStr = "This is a None test.";
        String result = Validate.stripNull(inputStr);
        assertFalse(result.contains("None"));
    }

    // Test IP address validation
    @Test
    void testIpHappy() {
        String[] validIps = {
            "192.168.1.1", "0.0.0.0", "255.255.255.255"
        };
        for (String ip : validIps) {
            assertTrue(Validate.ip(ip));
        }
    }

    // Test abuse cases for IP address validation
    @Test
    void testIpAbuse() {
        String[] invalidIps = {
            "192.168.1.999", "1a92.168.1.1", "256.256.256.256", "abc.def.ghi.jkl", "192.168.1"
        };
        for (String ip : invalidIps) {
            assertFalse(Validate.ip(ip));
        }
    }

    // Test MAC address validation
    @Test
    void testMacHappy() {
        assertTrue(Validate.mac("00:1A:2B:3C:4D:5E"));
        assertTrue(Validate.mac("00-1A-2B-3C-4D-5E"));
        assertTrue(Validate.mac("00 1A 2B 3C 4D 5E"));
    }

    // Test abuse cases for MAC address validation
    @Test
    void testMacAbuse() {
        assertFalse(Validate.mac("00:1A:2B:3C:4D"));
        assertFalse(Validate.mac("00:1A:2B:3C:4D:GG"));
        assertFalse(Validate.mac("00 1A 2B 3C 4D 5E 6F"));
        assertFalse(Validate.mac("001A2B3C4D5E"));
    }

    // Test MD5 hash validation
    @Test
    void testMd5Happy() {
        assertTrue(Validate.md5("d41d8cd98f00b204e9800998ecf8427e"));
        assertTrue(Validate.md5("098f6bcd4621d373cade4e832627b4f6"));
    }

    // Test abuse cases for MD5 validation
    @Test
    void testMd5Abuse() {
        assertFalse(Validate.md5("d41d8cd98f00b204e9800998ecf8427"));
        assertFalse(Validate.md5("z41d8cd98f00b204e9800998ecf8427e"));
    }
}