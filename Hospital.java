package nyp2025Proje_AtaBas;

import java.io.Serializable;
import java.util.LinkedList;

public class Hospital implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final int id;
	private final String name;
	private LinkedList<Section> sections;
	
	
	public Hospital(int id, String name) {
		
		this.id = id;
		this.name = name;
		sections = new LinkedList<>();
	}


	
	
	
	public void addSection(Section section) throws DuplicateInfoException {
        for (Section sec : sections) {
            if (sec.getName().equals(section.getName())) {
                throw new DuplicateInfoException("Section with the same name already exists: " + section.getName());
            }
        }
        sections.add(section);
    }

    
    @SuppressWarnings("unused")
	private Section getSection(String sectionName) throws IDException {
        for (Section section : sections) {
            if (section.getName().equals(sectionName)) {
                return section;
            }
        }
        
        throw new IDException("Section not found with this name: " + sectionName);
    }
	
	
    public Section getSection(int sectionID) throws IDException {
        for (Section section : sections) {
            if (section.getId() == sectionID) {
                return section;
            }
        }
        
        throw new IDException("Section not found with this ID: " + sectionID );
    }
	
	
	
    public void removeSection(int sectionID) throws IDException {
        for (Section section : sections) {
            if (section.getId() == sectionID) {
                sections.remove(section);
                System.out.println("Section with ID " + sectionID + " has been removed successfully.");
                return;
            }
        }
        throw new IDException("Section not found with this ID: " + sectionID);
    
    
    }

   
    
    public void listSections() {
        if (sections.isEmpty()) {
            System.out.println("No sections available in this hospital.");
        } 
        else {
            for (Section section : sections) {
                System.out.println("Section ID: " + section.getId() + ", Name: " + section.getName());
            }
        }
    
    
    }
    
    
    
    
    
    
    
	
	
	public LinkedList<Section> getSections() {
		return sections;
	}


	public void setSections(LinkedList<Section> sections) {
		this.sections = sections;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
	
	
	
	
	

}
