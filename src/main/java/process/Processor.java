package process;

import model.TripleLong;
import model.groups.Group;
import model.groups.GroupedUniqueElements;

public class Processor {

    //Группа уникальных элементов.
    private Group uniqueElementsGroup;
    //Список сгруппированных уникальных элементов.
    private GroupedUniqueElements groupedUniqueElements;

    public Processor() {
        uniqueElementsGroup = new Group();
        groupedUniqueElements = new GroupedUniqueElements();
    }

    public Group getUniqueElementsGroup() {
        return uniqueElementsGroup;
    }

    public GroupedUniqueElements getGroupedUniqueElements() {
        return groupedUniqueElements;
    }

    public Processor(TripleLong tripleLong) {
        uniqueElementsGroup = new Group(tripleLong);
        groupedUniqueElements = new GroupedUniqueElements();
    }

    public void add(TripleLong tripleLong){
        if (!uniqueElementsGroup.isUnique(tripleLong)){
            //Трипллонг неуникален для группы уникальных элементов.
            //Сформировать группу и добавить (при необходимости, смерживая группы на ходу)
            //в список сгруппированных уникальных элементов.
            Group removed = uniqueElementsGroup.getGroupAndRemove(tripleLong);
            groupedUniqueElements.checkAndAddGroup(removed);
        }
        else
        {
            //Трипллонг уникален для группы уникальных элементов.
            //Проверить наличие трипллонга в списках сгруппированных уникальных элементов.
            if (groupedUniqueElements.isUnique(tripleLong)){
                // Трипллонг полностью уникален. Записать трипллонг в группу уникальных элементов
                uniqueElementsGroup.add(tripleLong);
            }
            else
            {
                //Трипллонг уникален для группы уникальных элементов.
                //Но неуникален для списка сгруппированных уникальных элементов.
                //Мержить группы по мере поиска
                groupedUniqueElements.merge(tripleLong);
            }
        }
    }
}
