package hue.com.mob201_ps08729.dao;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import hue.com.mob201_ps08729.model.KhoaHoc;
import hue.com.mob201_ps08729.model.kh;

public class KhDao {
    private FirebaseDatabase mdatabase;
    private DatabaseReference mReferencekh;
    private List<kh>khoaHocList= new ArrayList<>();
    public interface DataStatus{
        void upLoaded(List<kh>khoaHocList,List<String>keys);
        void insert();
        void update();
        void delete();

    }

    public KhDao(FirebaseDatabase mdatabase, DatabaseReference mReferenceBook, List<kh> khoaHocList) {
        this.mdatabase = mdatabase;
        this.mReferencekh = mReferenceBook;
        this.khoaHocList = khoaHocList;
    }

    public KhDao(){
        mdatabase=FirebaseDatabase.getInstance();
        mReferencekh=mdatabase.getReference("khoahoc");
    }
    public void readkh(final DataStatus dataStatus){
        mReferencekh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                khoaHocList.clear();
                List<String>keys= new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    kh khoaHoc= keyNode.getValue(kh.class);
                    khoaHocList.add(khoaHoc);
                }
                dataStatus.upLoaded(khoaHocList,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addkh(kh kh,final DataStatus dataStatus){
        String key= mReferencekh.push().getKey();
        mReferencekh.child(key).setValue(kh).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.insert();
            }
        });
    }

    public void delete(String key, final DataStatus dataStatus){
        mReferencekh.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.delete();
            }
        });

    }

    public void update(String key,kh kh,final DataStatus dataStatus){
        mReferencekh.child(key).setValue(kh).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.update();
            }
        });
    }

}
