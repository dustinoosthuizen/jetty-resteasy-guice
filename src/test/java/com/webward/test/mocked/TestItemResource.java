package com.webward.test.mocked;

import com.webward.dao.ItemDao;
import com.webward.dto.ItemDto;
import com.webward.entities.Item;
import com.webward.resources.ItemResource;
import com.webward.transformer.ItemTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestItemResource {

    @InjectMocks
    ItemResource itemResource;
    @Mock
    ItemDao itemDao;
    @Spy
    ItemTransformer itemTransformer;
    @Test
    public void testGetItemById()
    {
        Item item = new Item();
        item.setName("mocking is great");
        item.setId(1);

        given(itemDao.findByName(anyString())).willReturn(item);

        ItemDto anItem = itemResource.getItemById("mocking is great");

        then(anItem.getName()).isEqualTo("mocking is great");

    }

}