package testmock

import android.content.Context
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.data.model.room.DataDao
import com.example.practicaskotlin.data.model.room.DataRoomDatabase
import com.example.practicaskotlin.repository.DataRepository
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import java.io.IOException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MockTest {
    private lateinit var dataDao : DataDao
    private lateinit var db: DataRoomDatabase
    private lateinit var dataRepository: DataRepository
    @Mock
    lateinit var contextMock: Context

    @Mock
    private val observer: Observer<List<Data>>? = null


    @Before
    fun createDb(){
        MockitoAnnotations.initMocks(this)
        //  Mockito.mock(NewsDao::class.java)
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
            Mockito.mock(DataRoomDatabase::class.java)
        db = Room.inMemoryDatabaseBuilder(contextMock,DataRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
//        dataDao = db.dataDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB(){
        db.close()
    }

    @Test
    @Throws(IOException::class)
    suspend fun insertarData() {
        val data = db.dataDao()
//        val hdhd = listOf()
        data.insertData(Data("",""))
        data.insertData(Data("Name","Nombre"))
        data.insertData(Data("LastName","Apellido"))
        data.insertData(Data("Cat","Gato"))
        data.insertData(Data("Dog","Perro"))
        data.insertData(Data("Table","Mesa"))

        assertNotNull(data)
    }
// existsWord

/*
   // insert
        val insertedID = noteDAO!!.insertNote(note)
        assertNotNull(insertedID)

        // find by id
        val inserted = noteDAO!!.findById(insertedID)
        assertNotNull(inserted)
        assertTrue(inserted.text == note.text)    }
 @Test
    public void insert() throws Exception {
        // given
        Todo todo = new Todo("12345", "Mockito", "Time to learn something new");
        dao.selectAll().observeForever(observer);
        // when
        dao.insert(todo);
        // then
        verify(observer).onChanged(Collections.singletonList(todo));
    }
 */
}