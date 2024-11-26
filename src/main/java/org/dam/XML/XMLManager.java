package org.dam.XML;


import org.dam.Models.MarcaModel;
import org.dam.Models.MaterialModel;
import org.dam.Models.MuebleModel;
import org.dam.service.XMLService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.dam.service.XMLService.*;

public class XMLManager implements Exceptions {
    public static boolean createMueble(MuebleModel muebleModel) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        MuebleModel mueble = getMueble(muebleModel.getId_Mueble());
        if (mueble != null) {
            throw new Exception(ERROR9 + " id: " + mueble.getId_Mueble());
        }
        MarcaModel marcaModel = getMarcaModel(muebleModel.getMarcaModel().getId_Marca());
        if (marcaModel == null) {
            throw new Exception(ERROR3);
        }
        MaterialModel materialModel = getMaterialModel(muebleModel.getMaterialModel().getId_Material());
        if (materialModel == null) {
            throw new Exception(ERROR2);
        }

        try {
            Element muebleElement = document.createElement("Mueble");
            muebleElement.setAttribute("id", String.valueOf(muebleModel.getId_Mueble()));
            muebleElement.setAttribute("nombre", muebleModel.getNombre());
            muebleElement.setAttribute("id_material", String.valueOf(muebleModel.getMaterialModel().getId_Material()));
            muebleElement.setAttribute("precio", String.valueOf(muebleModel.getPrecio()));
            muebleElement.setAttribute("stock", String.valueOf(muebleModel.getStock()));
            muebleElement.setAttribute("id_marca", String.valueOf(muebleModel.getMarcaModel().getId_Marca()));
            muebleElement.setAttribute("isExterior", String.valueOf(muebleModel.isIs_Exterior()));
            muebleElement.setAttribute("fechaFabricacion", String.valueOf(muebleModel.getDate()));
            muebleElement.setAttribute("imagenPath", muebleModel.getImagenPath());
            NodeList nodeList = document.getElementsByTagName("Muebles");
            if (nodeList.getLength() > 0) {
                Element mueblesElement = (Element) nodeList.item(0);
                mueblesElement.appendChild(muebleElement);
            }
        } catch (Exception e) {
            throw new Exception(ERROR5);
        }
        return saveXML(document);
    }

    public static boolean createDefaultMarcas(ArrayList<MarcaModel> marcasModels) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        try {
            for (MarcaModel marcaModel : marcasModels) {
                Element marcaElement = document.createElement("Marca");
                marcaElement.setAttribute("id", String.valueOf(marcaModel.getId_Marca()));
                marcaElement.setAttribute("nombre", marcaModel.getNombre());
                NodeList marcasNodelist = document.getElementsByTagName("Marcas");
                if (marcasNodelist.getLength() > 0) {
                    Element marcasNodelistElement = (Element) marcasNodelist.item(0);
                    marcasNodelistElement.appendChild(marcaElement);
                }

            }
            return saveXML(document);
        } catch (NumberFormatException e) {
            throw new Exception("Error en el formato de la id");
        } catch (Exception e) {
            throw new Exception(ERROR4);
        }

    }

    public static boolean createDefaultMaterial(ArrayList<MaterialModel> materialModels) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        try {
            for (MaterialModel materialModel : materialModels) {
                Element materialElement = document.createElement("Material");
                materialElement.setAttribute("id", String.valueOf(materialModel.getId_Material()));
                materialElement.setAttribute("nombre", materialModel.getNombre());
                materialElement.setAttribute("precio", String.valueOf(materialModel.getPrecio()));
                NodeList materialNodelist = document.getElementsByTagName("Materiales");
                if (materialNodelist.getLength() > 0) {
                    Element materialsElement = (Element) materialNodelist.item(0);
                    materialsElement.appendChild(materialElement);
                }
            }
            return saveXML(document);
        } catch (NumberFormatException e) {
            throw new Exception("Error en el formato de la id o el precio");
        } catch (Exception e) {
            throw new Exception(ERROR3);
        }
    }

    public static MuebleModel getMueble(int id_muble) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }

        try {
            NodeList muebleNodeList = document.getElementsByTagName("Mueble");
            for (int i = 0; i < muebleNodeList.getLength(); i++) {
                Element muebleElement = (Element) muebleNodeList.item(i);
                if (muebleElement.getAttribute("id").equals(String.valueOf(id_muble))) {
                    return new MuebleModel(id_muble, Integer.parseInt(muebleElement.getAttribute("stock")),
                            Double.parseDouble(muebleElement.getAttribute("precio")), muebleElement.getAttribute("nombre"),
                            getMaterialModel(Integer.parseInt(muebleElement.getAttribute("id_material"))),
                            getMarcaModel(Integer.parseInt(muebleElement.getAttribute("id_marca"))),
                            LocalDate.parse(muebleElement.getAttribute("fechaFabricacion")),
                            Boolean.parseBoolean(muebleElement.getAttribute("isExterior")));
                }
            }
        } catch (Exception e) {
            throw new Exception(ERROR7);
        }
        return null;

    }

    public static ArrayList<MuebleModel> getMuebles() throws Exception {
        ArrayList<MuebleModel> muebleModels = new ArrayList<>();
        Document document = XMLService.loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }

        try {
            NodeList muebleNodeList = document.getElementsByTagName("Mueble");
            for (int i = 0; i < muebleNodeList.getLength(); i++) {
                Element muebleElement = (Element) muebleNodeList.item(i);
                muebleModels.add(new MuebleModel(Integer.parseInt(muebleElement.getAttribute("id")), Integer.parseInt(muebleElement.getAttribute("stock")),
                        Double.parseDouble(muebleElement.getAttribute("precio")), muebleElement.getAttribute("nombre"),
                        getMaterialModel(Integer.parseInt(muebleElement.getAttribute("id_material"))),
                        getMarcaModel(Integer.parseInt(muebleElement.getAttribute("id_marca"))),
                        LocalDate.parse(muebleElement.getAttribute("fechaFabricacion")),
                        Boolean.parseBoolean(muebleElement.getAttribute("isExterior")), muebleElement.getAttribute("imagenPath")));
            }
            return muebleModels;
        } catch (Exception e) {
            throw new Exception(ERROR7);
        }
    }

    public static ArrayList<MuebleModel> getMueblesByTxtField(String claveString) throws Exception {
        ArrayList<MuebleModel> muebleModels = new ArrayList<>();

        Document document = XMLService.loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        if (claveString.isEmpty()) {
            return getMuebles();
        }else {
            claveString = claveString.toLowerCase();
        }
        try {
            NodeList muebleNodeList = document.getElementsByTagName("Mueble");
            for (int i = 0; i < muebleNodeList.getLength(); i++) {
                Element muebleElement = (Element) muebleNodeList.item(i);
                MuebleModel option = new MuebleModel(
                        Integer.parseInt(muebleElement.getAttribute("id")),
                        Integer.parseInt(muebleElement.getAttribute("stock")),
                        Double.parseDouble(muebleElement.getAttribute("precio")),
                        muebleElement.getAttribute("nombre"),
                        getMaterialModel(Integer.parseInt(muebleElement.getAttribute("id_material"))),
                        getMarcaModel(Integer.parseInt(muebleElement.getAttribute("id_marca"))),
                        LocalDate.parse(muebleElement.getAttribute("fechaFabricacion")),
                        Boolean.parseBoolean(muebleElement.getAttribute("isExterior")),
                        muebleElement.getAttribute("imagenPath")
                );
                if ((option.getMaterialModel().getNombre().toLowerCase().contains(claveString) ||
                        option.getMarcaModel().getNombre().toLowerCase().contains(claveString) ||
                        option.getNombre().toLowerCase().contains(claveString))
                        && !muebleModels.contains(option)) {
                    muebleModels.add(option);
                }

            }
            return muebleModels;
        } catch (Exception e) {
            throw new Exception(ERROR7);
        }
    }

    public static ArrayList<MuebleModel> getMueblesByDate(LocalDate inicio, LocalDate fin) throws Exception {
        ArrayList<MuebleModel> muebleModels = new ArrayList<>();
        Document document = XMLService.loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }

        try {
            NodeList muebleNodeList = document.getElementsByTagName("Mueble");
            for (int i = 0; i < muebleNodeList.getLength(); i++) {
                Element muebleElement = (Element) muebleNodeList.item(i);
                LocalDate fechaFabricacion = LocalDate.parse(muebleElement.getAttribute("fechaFabricacion"));
                if ((fechaFabricacion.isEqual(inicio) || fechaFabricacion.isAfter(inicio))
                        && (fechaFabricacion.isEqual(fin) || fechaFabricacion.isBefore(fin))) {
                    muebleModels.add(new MuebleModel(Integer.parseInt(muebleElement.getAttribute("id")), Integer.parseInt(muebleElement.getAttribute("stock")),
                            Double.parseDouble(muebleElement.getAttribute("precio")), muebleElement.getAttribute("nombre"),
                            getMaterialModel(Integer.parseInt(muebleElement.getAttribute("id_material"))),
                            getMarcaModel(Integer.parseInt(muebleElement.getAttribute("id_marca"))),
                            LocalDate.parse(muebleElement.getAttribute("fechaFabricacion")),
                            Boolean.parseBoolean(muebleElement.getAttribute("isExterior")), muebleElement.getAttribute("imagenPath")));

                }
            }
            return muebleModels;
        } catch (
                Exception e) {
            throw new Exception(ERROR7);
        }
    }

    public static MarcaModel getMarcaModel(int id_marca) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        try {
            NodeList marcaNodeList = document.getElementsByTagName("Marca");
            for (int i = 0; i < marcaNodeList.getLength(); i++) {
                Element MarcaElement = (Element) marcaNodeList.item(i);
                if (MarcaElement.getAttribute("id").equals(String.valueOf(id_marca))) {
                    return new MarcaModel(id_marca, MarcaElement.getAttribute("nombre"));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al encontrar la marca");
        }
        return null;
    }

    public static ArrayList<MarcaModel> getMarcaModelsForCombo() throws Exception {
        Document document = loadOrCreateXML();
        ArrayList<MarcaModel> marcasModels = new ArrayList<>();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        try {
            NodeList marcaNodeList = document.getElementsByTagName("Marca");
            for (int i = 0; i < marcaNodeList.getLength(); i++) {
                Element MarcaElement = (Element) marcaNodeList.item(i);
                marcasModels.add(new MarcaModel(Integer.parseInt(MarcaElement.getAttribute("id")), MarcaElement.getAttribute("nombre")));
            }
            return marcasModels;
        } catch (Exception e) {
            throw new Exception("Error al encontrar la marca");
        }
    }

    public static ArrayList<MaterialModel> getMarcaMaterialForCombo() throws Exception {
        Document document = loadOrCreateXML();
        ArrayList<MaterialModel> materialModels = new ArrayList<>();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        try {
            NodeList marcaNodeList = document.getElementsByTagName("Material");
            for (int i = 0; i < marcaNodeList.getLength(); i++) {
                Element materialElement = (Element) marcaNodeList.item(i);
                materialModels.add(new MaterialModel(Integer.valueOf(materialElement.getAttribute("id")),
                        materialElement.getAttribute("nombre"),
                        Double.parseDouble(materialElement.getAttribute("precio"))));
            }
            return materialModels;
        } catch (Exception e) {
            throw new Exception("Error al encontrar la material");
        }
    }

    public static MaterialModel getMaterialModel(int id_material) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR2);
        }
        try {
            NodeList materialNodelist = document.getElementsByTagName("Material");
            for (int i = 0; i < materialNodelist.getLength(); i++) {
                Element MaterialElement = (Element) materialNodelist.item(i);
                if (MaterialElement.getAttribute("id").equals(String.valueOf(id_material))) {
                    return new MaterialModel(id_material,
                            MaterialElement.getAttribute("nombre"),
                            Double.parseDouble(MaterialElement.getAttribute("precio")));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al encontrar la marca");
        }
        return null;
    }

    public static boolean updateMueble(int id_mueble, MuebleModel muebleModel) throws Exception {
        Document document = loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        MuebleModel mueble = getMueble(id_mueble);
        if (mueble == null) {
            throw new Exception(ERROR10 + " id: " + id_mueble);
        }
        MarcaModel marcaModel = getMarcaModel(muebleModel.getMarcaModel().getId_Marca());
        if (marcaModel == null) {
            throw new Exception(ERROR3 + "La marca: " + muebleModel.getMarcaModel().getNombre() + " no existe.");
        }
        MaterialModel materialModel = getMaterialModel(muebleModel.getMaterialModel().getId_Material());
        if (materialModel == null) {
            throw new Exception(ERROR2 + "El material: " + muebleModel.getMaterialModel().getNombre() + " no existe.");
        }

        try {
            NodeList muebleNodelist = document.getElementsByTagName("Mueble");
            if (muebleNodelist.getLength() > 0) {
                for (int i = 0; i < muebleNodelist.getLength(); i++) {
                    Element muebleElement = (Element) muebleNodelist.item(i);
                    muebleElement.setAttribute("nombre", muebleModel.getNombre());
                    muebleElement.setAttribute("id_material", String.valueOf(muebleModel.getMaterialModel().getId_Material()));
                    muebleElement.setAttribute("precio", String.valueOf(muebleModel.getPrecio()));
                    muebleElement.setAttribute("stock", String.valueOf(muebleModel.getStock()));
                    muebleElement.setAttribute("id_marca", String.valueOf(muebleModel.getMarcaModel().getId_Marca()));
                    muebleElement.setAttribute("isExterior", String.valueOf(muebleModel.isIs_Exterior()));
                    muebleElement.setAttribute("fechaFabricacion", String.valueOf(muebleModel.getDate()));
                    boolean oksave = XMLService.saveXML(document);
                    if (oksave) {
                        return true;
                    } else {
                        throw new Exception(ERRROR11);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new Exception(ERROR12);
    }

    public static boolean removeMuebleById(String id, String name) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if (document == null) {
            throw new Exception(ERROR1);
        }
        if (id == null && name == null) {
            throw new Exception(ERROR13);
        }
        try {
            NodeList nodeList = document.getElementsByTagName("Mueble");
            if (nodeList.getLength() == 0) {
                throw new Exception(ERROR10);
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element filmElement = (Element) nodeList.item(i);
                if (filmElement.getAttribute("nombre").equals(name) || filmElement.getAttribute("id").equals(id)) {
                    filmElement.getParentNode().removeChild(filmElement);
                    return XMLService.saveXML(document);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        throw new Exception("8. No se encuentra la id");
    }
}

