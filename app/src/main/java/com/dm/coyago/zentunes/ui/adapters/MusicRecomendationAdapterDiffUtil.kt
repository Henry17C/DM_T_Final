package com.dm.coyago.zentunes.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.data.network.entities.openwhyd.Track
import com.dm.coyago.zentunes.databinding.FragmentMusicRecomendationItemBinding
import com.dm.coyago.zentunes.databinding.SongItemsBinding
import com.dm.coyago.zentunes.logic.local.Song

class MusicRecomendationAdapterDiffUtil(private val onSelectItem:(Track)->Unit):ListAdapter<Track, MusicRecomendationAdapterDiffUtil.ViewHolderMusicRecomentation>(DiffUtilMusicRecomentationCallback) {

    class ViewHolderMusicRecomentation(view: View) : RecyclerView.ViewHolder(view){


        private var binding:  FragmentMusicRecomendationItemBinding= FragmentMusicRecomendationItemBinding.bind(view)

        fun render(item: Track, onSelectItem:(Track)->Unit
        ){



            try {
                binding.imageView.load(item.img.toString()) {
                    crossfade(true)
                    placeholder(R.drawable.image)
                    transformations(CircleCropTransformation())
                }
            } catch (e: Exception) {
                Log.e("ImageLoading", "Error al cargar la imagen: ${e.message}")
            }
            binding.txtArtist.text=item.uNm.toString()
            binding.txtTitle.text=item.name.toString()
            binding.txtScore.text=item.score.toString()
            binding.txtURL.text=item.trackUrl.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusicRecomentation {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderMusicRecomentation(inflater.inflate(R.layout.fragment_music_recomendation_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderMusicRecomentation, position: Int) {
        holder.render(getItem(position), onSelectItem)
        val song = getItem(position)
        // holder.render(song)

        // Configura el OnClickListener para el elemento actual
        holder.itemView.setOnClickListener {
            onSelectItem(song)
        }

    }


}

private object DiffUtilMusicRecomentationCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return (oldItem==newItem)
    }


}