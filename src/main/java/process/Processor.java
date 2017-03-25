package process;

import model.TripleLong;
import model.groups.Group;
import model.groups.ListOfGroupedElements;

public class Processor {

    private Group uniqueElementsGroup;

    private ListOfGroupedElements listOfGroupedElements;

    public Processor() {
        uniqueElementsGroup = new Group();
        listOfGroupedElements = new ListOfGroupedElements();
    }

    public ListOfGroupedElements getListOfGroupedElements() {
        return listOfGroupedElements;
    }

    public Processor(TripleLong tripleLong) {
        uniqueElementsGroup = new Group(tripleLong);
        listOfGroupedElements = new ListOfGroupedElements();
    }

    public void Add(TripleLong tripleLong){
        if (!uniqueElementsGroup.IsUnique(tripleLong)){
            //Трипллонг неуникален для первого списка. Сформировать группу и отправить на проверку в глубокую группу.
            Group removed = uniqueElementsGroup.GetGroupAndRemove(tripleLong);
            if (listOfGroupedElements.getGroups().size()==0)
                listOfGroupedElements.AddGroup(removed);
            else
            {
                //Проверить наличие группы во второых списках
                if (listOfGroupedElements.IsUnique(removed)){
                    //Группа уникальна в первом списке и уникальна для глубоких списков. Добавить группу во вторые списки
                    listOfGroupedElements.AddGroup(removed);
                }
                else {
                    //группа уникальна в первом списке, но неуникален для глубоких списков. Мержить группы по мере поиска
                    listOfGroupedElements.Merge(removed);
                }
            }

        }
        else
        {
            //Трипллонг уникален в первом списке, проверить наличие трипллонга во второых списках
            if (listOfGroupedElements.IsUnique(tripleLong)){
                // Трипллонг уникален для всего вычисления. Записать трипллонг в группу уникальных трипллонгов
                uniqueElementsGroup.Add(tripleLong);
            }
            else
            {
                //Трипллонг уникален в первом списке, но неуникален для глубоких списков. Мержить группы по мере поиска
                listOfGroupedElements.Merge(tripleLong);
            }
        }
    }
}
