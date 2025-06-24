package permcomb_got;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void combine() {
        assertEquals(
                List.of(
                        List.of(1, 2, 3),
                        List.of(1, 2, 4),
                        List.of(1, 3, 4),
                        List.of(2, 3, 4)),
                new Solution().combine(4, 3));
    }

    @Test
    void permute() {
        assertEquals(
                List.of(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1)),
                new Solution().permute(new int[]{1, 2, 3}));
    }
}
