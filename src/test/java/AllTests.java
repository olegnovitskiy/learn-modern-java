import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.shop.api.impl.ShopAPIImplTest;
import org.store.api.book.BookReaderImplTest;
import org.store.api.impl.StoreAPIImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ShopAPIImplTest.class,
        BookReaderImplTest.class,
        StoreAPIImplTest.class})

public class AllTests {}
